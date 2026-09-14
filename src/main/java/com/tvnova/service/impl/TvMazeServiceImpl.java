package com.tvnova.service.impl;

import com.tvnova.client.TvMazeClient;
import com.tvnova.dto.response.ShowResponse;
import com.tvnova.dto.tvmaze.TvMazeNetwork;
import com.tvnova.dto.tvmaze.TvMazeShow;
import com.tvnova.service.ITvMazeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TvMazeServiceImpl implements ITvMazeService {
  private final TvMazeClient tvMazeClient;

  @Override
  public List<ShowResponse> searchShows(String query) {
    return tvMazeClient.searchShows(query).stream()
      .map(result -> toShowMapping(result.getShow()))
      .toList();
  }

  private ShowResponse toShowMapping(TvMazeShow show) {
    return new ShowResponse(
      show.getId(),
      show.getName(),
      resolveChannel(show.getNetwork(), show.getWebChannel()),
      show.getSummary(),
      show.getGenres() != null ? show.getGenres() : List.of()
    );
  }

  private String resolveChannel(TvMazeNetwork network, TvMazeNetwork webChannel) {
    if (network != null && network.getName() != null) {
      return network.getName();
    }
    if (webChannel != null && webChannel.getName() != null) {
      return webChannel.getName();
    }
    return null;
  }
}
