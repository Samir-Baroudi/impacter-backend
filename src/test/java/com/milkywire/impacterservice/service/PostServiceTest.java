package com.milkywire.impacterservice.service;

import static org.junit.Assert.assertEquals;

import com.milkywire.impacterservice.domain.Post;
import com.milkywire.impacterservice.dto.PostDto;
import com.milkywire.impacterservice.utils.PostTestUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PostServiceTest {

    private final int POST_ID = 10;
    private final int IMPACTER_ID = 20;

    @InjectMocks
    PostService postService;

    @Test
    public void testMapDtoToDomain() {
        PostDto dto = PostTestUtil.createPostDto(POST_ID, IMPACTER_ID);
        Post domain = postService.mapToDomain(dto);

        assertAreSame(domain, dto);
    }

    @Test
    public void testMapDomainToDto() {
        Post domain = PostTestUtil.createPost(POST_ID, IMPACTER_ID);
        PostDto dto = postService.mapToDto(domain);

        assertAreSame(domain, dto);
    }

    private void assertAreSame(Post domain, PostDto dto) {
        assertEquals(domain.getId(), dto.getId());
        assertEquals(domain.getImpacterId(), dto.getImpacterId());
        assertEquals(domain.getType(), dto.getType());
        assertEquals(domain.getStatus(), dto.getStatus());
        assertEquals(domain.getData(), dto.getData());
        assertEquals(domain.getReactionCount(), dto.getReactionCount());
        assertEquals(domain.getPublishedAt(), dto.getPublishedAt());
    }
}
