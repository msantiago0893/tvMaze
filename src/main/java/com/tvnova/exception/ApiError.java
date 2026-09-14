package com.tvnova.exception;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Map;

public record ApiError(
  String code,
  String message,
  int status,
  LocalDateTime timestamp,
  String path,
  Map<String, String> details
) {}
