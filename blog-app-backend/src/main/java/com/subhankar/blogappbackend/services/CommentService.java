package com.subhankar.blogappbackend.services;

import com.subhankar.blogappbackend.dto.CommentDto;

import java.util.List;
import java.util.Set;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto, Integer postId, Integer userId);
    void deleteComment(Integer commentId);
    Set<CommentDto> getCommentByPostId(Integer id);
    Set<CommentDto> getCommentByUserId(Integer id);
    CommentDto updateComment(CommentDto commentDto, Integer id);

    CommentDto getCommentById(Integer id);

}
