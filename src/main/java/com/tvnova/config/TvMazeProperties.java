package com.tvnova.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@ConfigurationProperties(prefix = "tvmaze.client")
public record TvMazeProperties(
  String baseUrl,
  Duration connectTimeout,
  Duration readTimeout
) {}
