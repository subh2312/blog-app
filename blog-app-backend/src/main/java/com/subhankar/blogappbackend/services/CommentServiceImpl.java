package com.subhankar.blogappbackend.services;

import com.subhankar.blogappbackend.dto.CommentDto;
import com.subhankar.blogappbackend.entities.Comment;
import com.subhankar.blogappbackend.entities.Post;
import com.subhankar.blogappbackend.entities.User;
import com.subhankar.blogappbackend.exceptions.ResourceNotFoundException;
import com.subhankar.blogappbackend.repositories.CommentRepository;
import com.subhankar.blogappbackend.repositories.PostRepository;
import com.subhankar.blogappbackend.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId, Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
        Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","id",postId));
        Comment comment =modelMapper.map(commentDto, Comment.class);
        comment.setUser(user);
        comment.setPost(post);

        return modelMapper.map(commentRepository.save(comment), CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        commentRepository.delete(commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","Id",commentId)));

    }

    @Override
    public Set<CommentDto> getCommentByPostId(Integer id) {
        return  commentRepository.findAllByPost(postRepository.findById(id).orElseThrow(()->
                        new ResourceNotFoundException("Post","id",id)))
                .stream().map(comment -> modelMapper.map(comment,CommentDto.class)).collect(Collectors.toSet());
    }

    @Override
    public Set<CommentDto> getCommentByUserId(Integer id) {
        return  commentRepository.findAllByUser(userRepository.findById(id).orElseThrow(()->
                        new ResourceNotFoundException("User","id",id)))
                .stream().map(comment -> modelMapper.map(comment,CommentDto.class)).collect(Collectors.toSet());
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto, Integer id) {
        Comment comment =commentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Comment","Id",id));
        comment.setContent(commentDto.getContent());


        return modelMapper.map(commentRepository.save(comment),CommentDto.class);
    }

    @Override
    public CommentDto getCommentById(Integer id) {
        return modelMapper.map(commentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Comment","Id",id)),CommentDto.class);

    }
}
