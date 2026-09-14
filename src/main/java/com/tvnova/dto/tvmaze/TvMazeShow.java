package com.tvnova.dto.tvmaze;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TvMazeShow {

  @JsonProperty("id")
  private long id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("summary")
  private String summary;

  @JsonProperty("genres")
  private List<String> genres;

  @JsonProperty("network")
  private TvMazeNetwork network;

  @JsonProperty("webChannel")
  private TvMazeNetwork webChannel;
}
