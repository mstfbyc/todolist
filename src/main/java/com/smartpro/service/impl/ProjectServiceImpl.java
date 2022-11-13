package com.smartpro.service.impl;

import com.smartpro.dto.ProjectDto;
import com.smartpro.entity.Project;
import com.smartpro.entity.User;
import com.smartpro.repository.ProjectRepository;
import com.smartpro.repository.UserRepository;
import com.smartpro.service.ProjectService;
import com.smartpro.util.TPage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

   // @Autowired
   // private ProjectRepository projectRepository;
    private final ProjectRepository projectRepository;

    private final ModelMapper modelMapper;

    private final UserRepository userRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public ProjectDto save(ProjectDto projectDto) {
        Project projectCheck = projectRepository.findProjectByProjectCode(projectDto.getProjectCode()).orElse(null);
        if(projectCheck !=null){
            throw new IllegalArgumentException("Project code Already Exist");
        }
        Project project = modelMapper.map(projectDto,Project.class);
        User user = userRepository.findById(projectDto.getManagerId()).orElse(null);
        project.setManager(user);
        project = projectRepository.save(project);
        projectDto.setId(project.getId());
        return projectDto;
    }

    @Override
    public ProjectDto getById(Long id) {
        Project project = projectRepository.findById(id).orElse(new Project());
        return modelMapper.map(project, ProjectDto.class);
    }

    @Override
    public ProjectDto getByProjectCode(String projectCode) {

        return null;
    }

    @Override
    public List<ProjectDto> getByProjectCodeContains(String projectCode) {
        return null;
    }

    @Override
    public TPage<ProjectDto> getAllPageable(Pageable pageable) {
        Page<Project> data = projectRepository.findAll(pageable);
        TPage<ProjectDto> response = new TPage<>();
        response.setStat(data, Arrays.asList(modelMapper.map(data.getContent(),ProjectDto[].class)));
        return response;
    }

    @Override
    public List<ProjectDto> getAll() {
        List<Project> data = projectRepository.findAll();
        return Arrays.asList(modelMapper.map(data,ProjectDto[].class));
    }

    @Override
    public Boolean delete(ProjectDto projectDto) {
        projectRepository.delete(modelMapper.map(projectDto,Project.class));
        return Boolean.TRUE;
    }

    @Override
    public Boolean delete(Long id) {
        projectRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public ProjectDto update(Long id, ProjectDto projectDto) {
        Project project = projectRepository.findById(id).orElse(null);
        if(project == null){
            throw new IllegalArgumentException("Project Does Not Exist Id "+ id);
        }
        Project projectCheck = projectRepository.findProjectByProjectCodeAndIdNot(projectDto.getProjectCode(), id);
        if (projectCheck != null){
            throw new IllegalArgumentException("Project Code Already Exist");
        }
        project.setProjectCode(projectDto.getProjectCode());
        project.setProjectName(projectDto.getProjectName());
        projectRepository.save(project);
        return modelMapper.map(project, ProjectDto.class);
    }
}
