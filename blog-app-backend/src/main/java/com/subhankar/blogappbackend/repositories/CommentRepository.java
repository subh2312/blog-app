package com.subhankar.blogappbackend.repositories;

import com.subhankar.blogappbackend.entities.Comment;
import com.subhankar.blogappbackend.entities.Post;
import com.subhankar.blogappbackend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.Set;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
    Set<Comment> findAllByUser(User user);

    Set<Comment> findAllByPost(Post post);
}
