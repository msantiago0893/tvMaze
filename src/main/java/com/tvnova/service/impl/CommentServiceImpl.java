package com.tvnova.service.impl;

import com.tvnova.dto.request.CommentRequest;
import com.tvnova.dto.response.StatusResponse;
import com.tvnova.entity.CommentEntity;
import com.tvnova.repository.CommentRepository;
import com.tvnova.service.ICommentService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements ICommentService {

  private static final Logger LOG = LoggerFactory.getLogger(CommentServiceImpl.class);

  private final CommentRepository commentRepository;

  @Override
  public StatusResponse saveComment(CommentRequest request) {
    CommentEntity entity = new CommentEntity(
      null,
      request.showId(),
      request.comment(),
      request.rating(),
      LocalDateTime.now()
    );

    commentRepository.save(entity);

    LOG.info("Comentario guardado: {}", request.showId());

    return new StatusResponse("OK", "Comentario guardado con éxito");
  }
}
