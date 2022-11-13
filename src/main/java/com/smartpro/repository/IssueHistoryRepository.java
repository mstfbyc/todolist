package com.smartpro.repository;

import com.smartpro.entity.IssueHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueHistoryRepository extends JpaRepository<IssueHistory,Long> {
    List<IssueHistory> findByIssueIdOrderById(Long id);
}
