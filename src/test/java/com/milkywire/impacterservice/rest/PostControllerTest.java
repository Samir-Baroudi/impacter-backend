package com.milkywire.impacterservice.rest;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.milkywire.impacterservice.domain.Post;
import com.milkywire.impacterservice.dto.PostDto;
import com.milkywire.impacterservice.service.PostService;
import com.milkywire.impacterservice.utils.PostTestUtil;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PostControllerTest {


    @InjectMocks
    PostController postController;

    @Mock
    PostService postService;

    @Test
    public void testGetAllPosts() {
        final PostDto postDto1 = PostTestUtil.createPostDto(100, 10);
        final PostDto postDto2 = PostTestUtil.createPostDto(200, 20);
        final List<PostDto> postDtoList = List.of(postDto1, postDto2);

        when(postService.mapToDtoList(any())).thenReturn(postDtoList);

        List<PostDto> actualDtoList = postController.getAll();

        verify(postService).getAllPosts();
        verify(postService).mapToDtoList(any());
        assertEquals(postDtoList.size(), actualDtoList.size());

        actualDtoList.forEach(actualDto -> {
            for (PostDto postDto : postDtoList) {
                if (postDto.getId() == actualDto.getId()) {
                    assertAreSame(postDto, actualDto);
                }
            }
        });
    }

    @Test
    public void testGetAllForImpacter() {
        final int impacterId = 10;
        final PostDto postDto1 = PostTestUtil.createPostDto(100, impacterId);
        final PostDto postDto2 = PostTestUtil.createPostDto(200, impacterId);
        final List<PostDto> postDtoList = List.of(postDto1, postDto2);

        when(postService.mapToDtoList(any())).thenReturn(postDtoList);

        List<PostDto> actualDtoList = postController.getAllForImpacter(impacterId);

        verify(postService).getAllForImpacter(impacterId);
        verify(postService).mapToDtoList(any());
        assertEquals(postDtoList.size(), actualDtoList.size());

        actualDtoList.forEach(actualDto -> {
            for (PostDto postDto : postDtoList) {
                if (postDto.getId() == actualDto.getId()) {
                    assertAreSame(postDto, actualDto);
                }
            }
        });
    }

    @Test
    public void testGetPostByImpacter() {
        final int impacterId = 10;
        final int postId = 200;

        final Post post = PostTestUtil.createPost(postId, impacterId);
        final PostDto postDto = PostTestUtil.createPostDto(postId, impacterId);

        when(postService.getPostForImpacter(postId, impacterId)).thenReturn(post);
        when(postService.mapToDto(post)).thenReturn(postDto);

        PostDto actualDto = postController.getPostByImpacter(postId, impacterId);

        verify(postService).getPostForImpacter(postId, impacterId);
        verify(postService).mapToDto(post);

        assertAreSame(postDto, actualDto);
    }

    @Test
    public void testGetById() {
        final int impacterId = 1;
        final int postId = 100;

        final Post post = PostTestUtil.createPost(postId, impacterId);
        final PostDto postDto = PostTestUtil.createPostDto(postId, impacterId);

        when(postService.getById(postId)).thenReturn(post);
        when(postService.mapToDto(post)).thenReturn(postDto);

        PostDto actualDto = postController.getById(postId);

        verify(postService).getById(postId);
        verify(postService).mapToDto(post);

        assertAreSame(postDto, actualDto);
    }

    @Test
    public void testUpdatePost() {
        final int impacterId = 100;
        final int postId = 500;

        final Post post = PostTestUtil.createPost(0, impacterId);
        final PostDto postDto = PostTestUtil.createPostDto(postId, impacterId);

        when(postService.mapToDomain(postDto)).thenReturn(post);
        when(postService.updatePost(post)).thenReturn(post);
        when(postService.mapToDto(post)).thenReturn(postDto);

        PostDto actualDto = postController.updatePost(postId, postDto);

        verify(postService).mapToDomain(postDto);
        verify(postService).updatePost(post);
        verify(postService).mapToDto(post);

        assertAreSame(postDto, actualDto);
    }

    @Test
    public void testDeletePost() {
        final int postId = 40;

        postController.deletePost(postId);
        verify(postService).deletePost(postId);
    }

    @Test
    public void testDeleteUnValidPost() {
        final int postId = 0;

        postController.deletePost(postId);
        verify(postService, times(0)).deletePost(postId);
    }


    private void assertAreSame(PostDto dto1, PostDto dto2) {
        assertEquals(dto1.getId(), dto2.getId());
        assertEquals(dto1.getImpacterId(), dto2.getImpacterId());
        assertEquals(dto1.getType(), dto2.getType());
        assertEquals(dto1.getStatus(), dto2.getStatus());
        assertEquals(dto1.getData(), dto2.getData());
        assertEquals(dto1.getReactionCount(), dto2.getReactionCount());
        assertEquals(dto1.getPublishedAt(), dto2.getPublishedAt());
    }
}
