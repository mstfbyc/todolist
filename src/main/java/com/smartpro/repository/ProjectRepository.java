package com.smartpro.repository;

import com.smartpro.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.w3c.dom.ls.LSInput;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project,Long> {
    Optional<Project> findProjectByProjectCode(String projectCode);

    Project findProjectByProjectCodeAndIdNot(String projectCode, Long id);

    List<Project> findProjectByProjectCodeContains(String projectCode);

    Page<Project> findAll(Pageable pageable);
    List<Project> findAll(Sort sort);
}
