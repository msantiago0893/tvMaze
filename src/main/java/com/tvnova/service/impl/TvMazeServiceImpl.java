package com.tvnova.service.impl;

import com.tvnova.client.TvMazeClient;
import com.tvnova.dto.response.CommentResponse;
import com.tvnova.dto.response.ShowResponse;
import com.tvnova.dto.tvmaze.TvMazeNetwork;
import com.tvnova.dto.tvmaze.TvMazeShow;
import com.tvnova.entity.CommentEntity;
import com.tvnova.entity.ShowCacheEntity;
import com.tvnova.repository.CommentRepository;
import com.tvnova.repository.ShowCacheRepository;
import com.tvnova.service.ITvMazeService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class TvMazeServiceImpl implements ITvMazeService {

  private static final Logger LOG = LoggerFactory.getLogger(TvMazeServiceImpl.class);

  private final TvMazeClient tvMazeClient;
  private final ShowCacheRepository showCacheRepository;
  private final CommentRepository commentRepository;

  @Override
  public List<ShowResponse> searchShows(String query) {
    return tvMazeClient.searchShows(query).stream()
      .map(result -> {
        ShowResponse show = toShowMapping(result.getShow());

        show.setComments(getCommentsByShowId(show.getId()));

        return show;
      })
      .toList();
  }

  @Override
  public ShowResponse getShowById(long id) {
    LOG.info("Consultando Show con id {}", id);

    ShowResponse response = showCacheRepository.findById(id)
      .map(this::toShowResponse)
      .orElseGet(() -> fetchAndCache(id));

    response.setComments(getCommentsByShowId(id));
    return response;
  }

  private List<CommentResponse> getCommentsByShowId(long showId) {
    return commentRepository.findByShowId(showId).stream()
      .map(c -> new CommentResponse(c.getComment(), c.getRating()))
      .toList();
  }

  private ShowResponse fetchAndCache(long id) {
    LOG.info("Show {} no encontrado en cache, se consultará en API", id);

    TvMazeShow show = tvMazeClient.getShowById(id);

    ShowResponse response = toShowMapping(show);

    saveToCache(response);

    return response;
  }

  private void saveToCache(ShowResponse show) {
    ShowCacheEntity entity = new ShowCacheEntity(
      show.getId(),
      show.getName(),
      show.getChannel(),
      show.getSummary(),
      show.getGenres(),
      LocalDateTime.now()
    );
    showCacheRepository.save(entity);

    LOG.info("Show guardado en cache - mongodb");
  }

  private ShowResponse toShowResponse(ShowCacheEntity entity) {
    return new ShowResponse(
      entity.getId(),
      entity.getName(),
      entity.getChannel(),
      entity.getSummary(),
      entity.getGenres() != null ? entity.getGenres() : List.of(),
      List.of()
    );
  }

  private ShowResponse toShowMapping(TvMazeShow show) {
    return new ShowResponse(
      show.getId(),
      show.getName(),
      resolveChannel(show.getNetwork(), show.getWebChannel()),
      show.getSummary(),
      show.getGenres() != null ? show.getGenres() : List.of(),
      List.of()
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
