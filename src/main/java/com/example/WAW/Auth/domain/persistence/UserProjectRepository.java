package com.example.WAW.Auth.domain.persistence;

import com.example.WAW.Auth.domain.model.entity.UserProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProjectRepository extends JpaRepository<UserProject, Long> {
}
