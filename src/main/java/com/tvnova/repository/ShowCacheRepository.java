package com.tvnova.repository;

import com.tvnova.entity.ShowCacheEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShowCacheRepository extends MongoRepository<ShowCacheEntity, Long> {
}
