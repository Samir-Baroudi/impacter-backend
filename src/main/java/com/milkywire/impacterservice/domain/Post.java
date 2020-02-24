package com.milkywire.impacterservice.domain;

import lombok.Data;

@Data
public class Post {

    private int id;
    private String description;
    private String type;
    private String status;
    private String data;
    private int reactionCount;
    private int impacterId;
    private long publishedAt;
}
