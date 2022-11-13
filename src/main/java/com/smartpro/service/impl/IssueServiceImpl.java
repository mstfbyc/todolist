package com.smartpro.service.impl;

import com.smartpro.dto.IssueDto;
import com.smartpro.entity.Issue;
import com.smartpro.enums.IssueStatusEnum;
import com.smartpro.repository.IssueHistoryRepository;
import com.smartpro.repository.IssueRepository;
import com.smartpro.repository.ProjectRepository;
import com.smartpro.repository.UserRepository;
import com.smartpro.service.IssueService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class IssueServiceImpl implements IssueService {
    //Spring bu kullanımı tavsiye etmiyor fakat çok fazla injection olduğu durumlar kullanılmalıdır.
/*    @Autowired
    private IssueRepository issueRepository; */

    private final IssueRepository issueRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final IssueHistoryRepository issueHistoryRepository;
    private final ModelMapper modelMapper;

    public IssueServiceImpl(IssueRepository issueRepository, UserRepository userRepository, ProjectRepository projectRepository, IssueHistoryRepository issueHistoryRepository, ModelMapper modelMapper) {
        this.issueRepository = issueRepository;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.issueHistoryRepository = issueHistoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public IssueDto save(IssueDto issueDto) {
        issueDto.setDate(new Date());
        issueDto.setIssueStatus(IssueStatusEnum.OPEN);

        Issue issue = modelMapper.map(issueDto,Issue.class);
        issue.setProject(projectRepository.findById(issueDto.getProjectId()).get());
        issue = issueRepository.save(issue);
        issueDto.setId(issue.getId());
        return issueDto;
    }

    @Override
    public IssueDto getById(Long id) {
        Issue issue = issueRepository.findById(id).orElse(new Issue());
        return modelMapper.map(issue,IssueDto.class);
    }

    @Override
    public Boolean delete(Long issue) {
        issueRepository.deleteById(issue);
        return true;
    }

    @Override
    public IssueDto update(Long id, IssueDto project) {

        return null;
    }
}
