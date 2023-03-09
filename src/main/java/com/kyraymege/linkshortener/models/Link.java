package com.kyraymege.linkshortener.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Optional;

@Document(collection = "link")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Link {
    @Id
    private ObjectId _id;
    private String code;
    private String url;
    private Long click_count;
    private LocalDateTime createdAt;
}
