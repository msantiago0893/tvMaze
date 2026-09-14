package com.tvnova.repository;

import com.tvnova.entity.CommentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<CommentEntity, String> {
}
