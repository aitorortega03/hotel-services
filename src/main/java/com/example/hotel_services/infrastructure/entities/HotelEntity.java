package com.example.hotel_services.infrastructure.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RedisHash("hotel")
public class HotelEntity {
  @Id
  private String id;
  private String hotelName;
}
