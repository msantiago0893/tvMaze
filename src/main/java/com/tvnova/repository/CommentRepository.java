package com.tvnova.repository;

import com.tvnova.entity.CommentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<CommentEntity, String> {
  List<CommentEntity> findByShowId(long showId);
}
