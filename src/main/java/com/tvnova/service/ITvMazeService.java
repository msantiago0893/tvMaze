package com.tvnova.service;

import com.tvnova.dto.response.ShowResponse;

import java.util.List;

public interface ITvMazeService {
  List<ShowResponse> searchShows(String query);

  ShowResponse getShowById(long id);
}
