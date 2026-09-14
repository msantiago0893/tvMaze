package com.tvnova.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(ExternalServiceException.class)
  public ResponseEntity<ApiError> handleExternalService(
    ExternalServiceException ex, HttpServletRequest request
  ) {
    return response(HttpStatus.BAD_GATEWAY, "EXTERNAL_SERVICE_ERROR", ex.getMessage(), request);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiError> handleUnexpected(
    Exception ex, HttpServletRequest request
  ) {
    LOG.error("Error del servidor: {}", ex.getClass().getSimpleName(), ex);
    return response(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_ERROR",
      "Ocurrio un error inesperado. Intente más tarde!!", request);
  }

  private ResponseEntity<ApiError> response(
    HttpStatus status, String code, String message, HttpServletRequest request
  ) {
    ApiError error = new ApiError(
      code, message, status.value(), LocalDateTime.now(), request.getRequestURI(), Map.of()
    );
    return ResponseEntity.status(status).body(error);
  }
}
