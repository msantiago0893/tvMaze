package com.tvnova.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
@EnableConfigurationProperties(TvMazeProperties.class)
public class RestClientConfig {

  @Bean
  public RestClient tvMazeRestClient(TvMazeProperties properties) {
    SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
    factory.setConnectTimeout(properties.connectTimeout());
    factory.setReadTimeout(properties.readTimeout());

    return RestClient.builder()
      .baseUrl(properties.baseUrl())
      .requestFactory(factory)
      .defaultHeader("User-Agent", "TvNova-API/1.0")
      .build();
  }
}
