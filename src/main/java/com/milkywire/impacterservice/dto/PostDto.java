package com.milkywire.impacterservice.dto;

import java.sql.Date;
import lombok.Data;

@Data
public class PostDto {

    private int id;
    private String description;
    private String type;
    private String status;
    private String data;
    private int reactionCount;
    private long impacterId;
    private String publishedAt;
}
