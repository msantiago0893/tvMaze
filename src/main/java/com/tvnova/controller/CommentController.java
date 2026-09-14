package com.tvnova.controller;

import com.tvnova.dto.request.CommentRequest;
import com.tvnova.dto.response.StatusResponse;
import com.tvnova.service.ICommentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

  private final ICommentService commentService;

  @PostMapping
  public ResponseEntity<StatusResponse> save(@Valid @RequestBody CommentRequest request) {
    StatusResponse response = commentService.saveComment(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }
}
