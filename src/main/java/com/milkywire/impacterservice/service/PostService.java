package com.milkywire.impacterservice.service;

import com.milkywire.impacterservice.dao.PostDao;
import com.milkywire.impacterservice.domain.Post;
import com.milkywire.impacterservice.dto.PostDto;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

    @Inject
    PostDao postDao;

    public List<Post> getAllForImpacter(long impacterId) {
        return postDao.findAllForImpacter(impacterId);
    }

    public List<Post> getAllPosts() {
        return postDao.findAll();
    }

    public Post getById(long postId) {
        return postDao.find(postId);
    }

    public Post getPostForImpacter(long postId, long impacterId) {
        return postDao.findPostForImpacter(postId, impacterId);
    }

    public Post updatePost(Post post) {
        return postDao.update(post);
    }

    public void deletePost(int postId) {
        postDao.delete(postId);
    }

    public List<PostDto> mapToDtoList(List<Post> domainList) {
        return domainList.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public PostDto mapToDto(Post domain) {
        PostDto dto = new PostDto();
        dto.setId(domain.getId());
        dto.setDescription(domain.getDescription());
        dto.setType(domain.getType());
        dto.setStatus(domain.getStatus());
        dto.setData(domain.getData());
        dto.setReactionCount(domain.getReactionCount());
        dto.setImpacterId(domain.getImpacterId());
        dto.setPublishedAt(domain.getPublishedAt());

        return dto;
    }

    public Post mapToDomain(PostDto dto) {
        Post domain = new Post();
        domain.setId(dto.getId());
        domain.setDescription(dto.getDescription());
        domain.setType(dto.getType());
        domain.setStatus(dto.getStatus());
        domain.setData(dto.getData());
        domain.setReactionCount(dto.getReactionCount());
        domain.setImpacterId(dto.getImpacterId());
        domain.setPublishedAt(dto.getPublishedAt());

        return domain;
    }
}
