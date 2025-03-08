package com.example.hotel_services.application.services.impl;

import com.example.hotel_services.application.services.HotelService;
import com.example.hotel_services.domain.request.HotelCreateRequest;
import com.example.hotel_services.infrastructure.entities.HotelEntity;
import com.example.hotel_services.infrastructure.repository.HotelRedisRepository;
import lombok.AllArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@AllArgsConstructor
public class HotelServiceImpl implements HotelService {

  private final StreamBridge streamBridge;

  private final HotelRedisRepository hotelRedisRepository;

  @Override
  public Mono<Long> createHotel(HotelCreateRequest hotelCreateRequest) {
    return Mono.just(hotelCreateRequest)
        .map(this::toEntity)
        .flatMap(this::publishHotel);
  }

  @Override
  public Mono<List<HotelEntity>> getHotels() {
    return hotelRedisRepository.findAll()
        .collectList();
  }

  private Mono<Long> publishHotel(HotelEntity hotelEntity){
    return Mono.just(hotelEntity)
        .doOnNext(hotel -> streamBridge.send("writeCache-out-0", hotel))
        .flatMap(hotel -> Mono.just(1L))
        .switchIfEmpty(Mono.just(0L));
  }

  private HotelEntity toEntity(HotelCreateRequest request){
    return HotelEntity.builder()
        .hotelName(request.getHotelName())
        .build();
  }
}
