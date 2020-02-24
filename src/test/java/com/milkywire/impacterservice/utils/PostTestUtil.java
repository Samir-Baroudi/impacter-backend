package com.milkywire.impacterservice.utils;

import com.milkywire.impacterservice.domain.Post;
import com.milkywire.impacterservice.dto.PostDto;

public class PostTestUtil {

    private final static String TYPE = "TEXT";
    private final static String STATUS = "HIDDEN";
    private final static String DATA = "{\"media\": [{\"image\": \"https://some-url/111\"}]}";
    private final static int REACTION_COUNT = 99;
    private final static long PUBLISHED_AT = 1580565600000L;

    public static PostDto createPostDto(int postId, int imapcterId) {
        PostDto postDto = new PostDto();
        postDto.setId(postId);
        postDto.setImpacterId(imapcterId);
        postDto.setType(TYPE);
        postDto.setStatus(STATUS);
        postDto.setData(DATA);
        postDto.setReactionCount(REACTION_COUNT);
        postDto.setPublishedAt(PUBLISHED_AT);

        return postDto;
    }

    public static Post createPost(int postId, int imapcterId) {
        Post post = new Post();
        post.setId(postId);
        post.setImpacterId(imapcterId);
        post.setType(TYPE);
        post.setStatus(STATUS);
        post.setData(DATA);
        post.setReactionCount(REACTION_COUNT);
        post.setPublishedAt(PUBLISHED_AT);

        return post;
    }
}
