package com.smartpro.service;

import com.smartpro.dto.ProjectDto;
import com.smartpro.util.TPage;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProjectService {
    ProjectDto save(ProjectDto projectDto);
    ProjectDto getById(Long id);
    ProjectDto getByProjectCode(String projectCode);
    List<ProjectDto> getByProjectCodeContains(String projectCode);
    TPage<ProjectDto> getAllPageable(Pageable pageable);
    List<ProjectDto> getAll();
    Boolean delete (ProjectDto projectDto);
    Boolean delete (Long id);
    ProjectDto update(Long id, ProjectDto projectDto);
}
