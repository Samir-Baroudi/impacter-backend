package com.milkywire.impacterservice.domain;

import java.util.Date;
import lombok.Data;

@Data
public class Post {

    private int id;
    private String description;
    private String type;
    private String status;
    private String data;
    private int reactionCount;
    private long impacterId;
    private Date publishedAt;
}
