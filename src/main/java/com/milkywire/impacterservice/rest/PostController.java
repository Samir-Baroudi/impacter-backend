package com.milkywire.impacterservice.rest;

import com.milkywire.impacterservice.domain.Post;
import com.milkywire.impacterservice.dto.PostDto;
import com.milkywire.impacterservice.service.PostService;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
public class PostController {

    public static final String IMPACTER_ID = "impacterId";
    public static final String POST_ID = "postId";

    @Inject
    private PostService postService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<PostDto> getAll() {
        List<Post> posts = postService.getAllPosts();
        List<PostDto> postDtoList = posts.stream().map(postService::mapToDto).collect(Collectors.toList());

        return postDtoList;
    }

    @GetMapping("/impacters/{" + IMPACTER_ID + "}/all")
    @ResponseStatus(HttpStatus.OK)
    public List<PostDto> getAllForImpacter(@PathVariable(IMPACTER_ID) int impacterId) {
        List<Post> posts = postService.getAllForImpacter(impacterId);
        List<PostDto> postDtoList = posts.stream().map(postService::mapToDto).collect(Collectors.toList());

        return postDtoList;
    }

    @GetMapping("/{" + POST_ID + "}/impacters/{" + IMPACTER_ID + "}")
    @ResponseStatus(HttpStatus.OK)
    public PostDto getPostByImpacter(@PathVariable(POST_ID) int postId, @PathVariable(IMPACTER_ID) int impacterId) {
        Post post = postService.getPostForImpacter(postId, impacterId);

        return postService.mapToDto(post);
    }

    @GetMapping("/{" + POST_ID + "}")
    @ResponseStatus(HttpStatus.OK)
    public PostDto getById(@PathVariable(POST_ID) int postId) {
        Post post = postService.getById(postId);

        return postService.mapToDto(post);
    }

    @PutMapping(value = "/{" + POST_ID + "}/update")
    @ResponseStatus(value = HttpStatus.OK)
    public PostDto updatePost(@PathVariable(POST_ID) int postId, @Valid @RequestBody PostDto postDto) {
        Post updatedPost = new Post();

        if (postId > 0) {
            postDto.setId(postId);
            Post post = postService.mapToDomain(postDto);
            updatedPost = postService.updatePost(post);
        }

        return postService.mapToDto(updatedPost);
    }

    @DeleteMapping(value = "/{" + POST_ID + "}/delete")
    @ResponseStatus(value = HttpStatus.OK)
    public void DeletePost(@PathVariable(POST_ID) int postId) {
        if (postId > 0) {
            postService.deletePost(postId);
        }
    }
}

