package com.example.hotel_services.infrastructure.config;

import com.example.hotel_services.infrastructure.entities.HotelEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

@Configuration
public class RedisConfig {

  @Bean
  @Primary
  public ReactiveRedisConnectionFactory reactiveRedisConnectionFactory() {
    RedisStandaloneConfiguration config = new RedisStandaloneConfiguration("cache", 6379);
    return new LettuceConnectionFactory(config);
  }

  @Bean
  public ReactiveRedisOperations<String, HotelEntity>redisOperations(ReactiveRedisConnectionFactory factory){
    Jackson2JsonRedisSerializer<HotelEntity> serializer = new Jackson2JsonRedisSerializer<>(HotelEntity.class);

    RedisSerializationContext.RedisSerializationContextBuilder<String, HotelEntity> builder =
        RedisSerializationContext.newSerializationContext(new Jackson2JsonRedisSerializer<>(HotelEntity.class));

    RedisSerializationContext<String, HotelEntity> context = builder.value(serializer).build();
    return new ReactiveRedisTemplate<>(factory, context);
  }
}