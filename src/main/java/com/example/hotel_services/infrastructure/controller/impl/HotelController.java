package com.example.hotel_services.infrastructure.controller.impl;

import com.example.hotel_services.application.services.HotelService;
import com.example.hotel_services.domain.request.HotelCreateRequest;
import com.example.hotel_services.infrastructure.controller.HotelApi;
import com.example.hotel_services.infrastructure.entities.HotelEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HotelController implements HotelApi {

  private final HotelService hotelService;

  @Override
  public Mono<ResponseEntity<Long>> createHotel(HotelCreateRequest request) {
    return hotelService.createHotel(request)
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.badRequest().build());
  }

  @Override
  public Mono<ResponseEntity<List<HotelEntity>>> getHotels() {
    return hotelService.getHotels()
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }
}
