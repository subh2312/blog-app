package com.subhankar.blogappbackend.dto;

import com.subhankar.blogappbackend.entities.Category;
import com.subhankar.blogappbackend.entities.Comment;
import com.subhankar.blogappbackend.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {
    private int id;

    private String title;

    private String content;

    private String imageName;

    private Date createdDate;

    private CategoryDto category;

    private UserDto user;

    private Set<Comment> comment = new HashSet<>();

}
