package com.tvnova.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CommentRequest(
  @NotNull(message = "ShowId es obligatorio")
  Long showId,

  @NotBlank(message = "Comentario es obligatorio")
  String comment,

  @NotNull(message = "El rating es obligatorio")
  @Min(value = 0, message = "El rating minimo es 0")
  @Max(value = 5, message = "El rating maximo es 5")
  Integer rating
) {}
