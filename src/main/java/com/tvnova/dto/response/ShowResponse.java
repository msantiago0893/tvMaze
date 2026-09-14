package com.tvnova.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ShowResponse {
  private long id;
  private String name;
  private String channel;
  private String summary;
  private List<String> genres;
}
