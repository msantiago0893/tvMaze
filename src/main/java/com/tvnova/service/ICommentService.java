package com.tvnova.service;

import com.tvnova.dto.request.CommentRequest;
import com.tvnova.dto.response.StatusResponse;

public interface ICommentService {
  StatusResponse saveComment(CommentRequest request);
}
