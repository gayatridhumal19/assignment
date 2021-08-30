package com.clairvoyant.weather.router;

import com.clairvoyant.weather.handler.CityHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class CityRouter {

  @Bean
  public RouterFunction<ServerResponse> route(CityHandler handler) {

    return RouterFunctions.route()
        .GET("CITY_WEATHER", handler::getCityList)
        .GET("CITY_WEATHER/{name}", RequestPredicates.accept(MediaType.APPLICATION_JSON),
            handler::getCityByName)
        .PUT("CITY_WEATHER", RequestPredicates.contentType(MediaType.APPLICATION_JSON),
            handler::updateCity)
        .DELETE("CITY_WEATHER/{id}", RequestPredicates.accept(MediaType.TEXT_PLAIN),
            handler::deleteCityById)
        .build();

  }
}
