package com.smartpro.repository;

import com.smartpro.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IssueRepository extends JpaRepository<Issue,Long> {
}
