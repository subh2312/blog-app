package com.subhankar.blogappbackend.repositories;

import com.subhankar.blogappbackend.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Rolerepository extends JpaRepository<Role,Integer> {
}
