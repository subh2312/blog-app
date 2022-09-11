package com.subhankar.blogappbackend.repositories;

import com.subhankar.blogappbackend.entities.Category;
import com.subhankar.blogappbackend.entities.Post;
import com.subhankar.blogappbackend.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {

    List<Post> findAllByUser(User user);
    List<Post> findAllByCategory(Category category);

    Page<Post> findByTitleContaining(String keyword, Pageable pageable);

}
