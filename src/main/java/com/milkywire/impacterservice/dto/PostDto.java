package com.milkywire.impacterservice.dto;

import lombok.Data;

@Data
public class PostDto {

    private int id;
    private String description;
    private String type;
    private String status;
    private String data;
    private int reactionCount;
    private int impacterId;
    private long publishedAt;
}
