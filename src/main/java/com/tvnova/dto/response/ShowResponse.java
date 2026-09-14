package com.tvnova.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ShowResponse {
  private Integer id;
  private String name;
  private String channel;
  private String summary;
  private List<String> genres;
}
