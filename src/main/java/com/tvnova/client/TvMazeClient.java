package com.tvnova.client;

import com.tvnova.dto.tvmaze.TvMazeSearchResult;
import com.tvnova.dto.tvmaze.TvMazeShow;
import com.tvnova.exception.ExternalServiceException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
@AllArgsConstructor
public class TvMazeClient {
  private final RestClient tvMazeRestClient;

  public List<TvMazeSearchResult> searchShows(String query) {
    try {
      List<TvMazeSearchResult> results = tvMazeRestClient.get()
        .uri(uriBuilder -> uriBuilder
          .path("/search/shows")
          .queryParam("q", query)
          .build())
        .retrieve()
        .body(new ParameterizedTypeReference<>() {});

      return results != null ? results : List.of();
    } catch (Exception ex) {
      throw new ExternalServiceException("Error al conectar con API TV Maze: " + ex.getMessage());
    }
  }

  public TvMazeShow getShowById(long id) {
    try {
      return tvMazeRestClient.get()
        .uri("/shows/{id}", id)
        .retrieve()
        .body(TvMazeShow.class);
    } catch (Exception ex) {
      throw new ExternalServiceException("Error al conectar con API TV Maze: " + ex.getMessage());
    }
  }
}
