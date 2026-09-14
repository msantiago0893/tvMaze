package com.tvnova.controller;

import com.tvnova.dto.response.ShowResponse;
import com.tvnova.service.ITvMazeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/shows")
public class ShowController {

  private final ITvMazeService tvMazeService;

  @GetMapping("/search")
  public ResponseEntity<List<ShowResponse>> search(
    @RequestParam(name = "search_query") String query
  ) {
    List<ShowResponse> shows = tvMazeService.searchShows(query);
    return ResponseEntity.ok(shows);
  }
}
