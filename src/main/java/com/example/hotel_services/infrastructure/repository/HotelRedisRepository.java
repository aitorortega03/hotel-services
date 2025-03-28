package com.example.hotel_services.infrastructure.repository;

import com.example.hotel_services.infrastructure.entities.HotelEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class HotelRedisRepository {

  private final ReactiveRedisOperations<String, HotelEntity> reactiveRedisOperations;

  public Flux<HotelEntity> findAll(){
    return reactiveRedisOperations.opsForList()
        .range("hotel", 0, -1);
  }

  public Mono<Long> save(HotelEntity hotelEntity){
    hotelEntity.setId(UUID.randomUUID().toString());
    return reactiveRedisOperations.opsForList()
        .rightPush("hotel", hotelEntity);
  }
}
