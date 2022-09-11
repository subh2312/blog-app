package com.subhankar.blogappbackend.dto;

import com.subhankar.blogappbackend.entities.Post;
import com.subhankar.blogappbackend.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto {
    private Integer id;
    private String content;
    private UserDto user;
}
