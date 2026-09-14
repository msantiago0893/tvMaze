package com.tvnova.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "shows")
@NoArgsConstructor
@AllArgsConstructor
public class ShowCacheEntity {
  @Id
  private Long id;
  private String name;
  private String channel;
  private String summary;
  private List<String> genres;
  private LocalDateTime cachedAt;
}
