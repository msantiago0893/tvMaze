package com.tvnova.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "comments")
@NoArgsConstructor
@AllArgsConstructor
public class CommentEntity {

  @Id
  private String id;

  private long showId;

  private String comment;

  private int rating;
  
  private LocalDateTime createdAt;
}
