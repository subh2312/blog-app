package com.subhankar.blogappbackend.services;

import com.subhankar.blogappbackend.dto.PostDto;
import com.subhankar.blogappbackend.dto.PostDto;
import com.subhankar.blogappbackend.entities.Post;
import com.subhankar.blogappbackend.payloads.GetAllResponse;
import lombok.Getter;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto,Integer categoryId, Integer userId);
    PostDto updatePost(PostDto postDto, Integer postId);
    PostDto getPostById(Integer id);
    GetAllResponse getAllPosts(Integer pageNumber, Integer pageSize,String sortBy, String sortDir);

    List<PostDto> getPostByCategory(Integer id);

    List<PostDto> getPostByUser(Integer id);

    GetAllResponse searchPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir, String keyword);
    void deletePost(Integer postId);
}
