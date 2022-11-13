package com.smartpro.service.impl;

import com.smartpro.dto.IssueHistoryDto;
import com.smartpro.entity.Issue;
import com.smartpro.entity.IssueHistory;
import com.smartpro.repository.IssueHistoryRepository;
import com.smartpro.service.IssueHistoryService;
import com.smartpro.util.TPage;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class IssueHistoryServiceImpl implements IssueHistoryService {

    private final IssueHistoryRepository issueHistoryRepository;

    private final ModelMapper modelMapper;

    public IssueHistoryServiceImpl(IssueHistoryRepository issueHistoryRepository, ModelMapper modelMapper) {
        this.issueHistoryRepository = issueHistoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public IssueHistoryDto save(IssueHistoryDto issueHistoryDto) {
        IssueHistory issueHistory = modelMapper.map(issueHistoryDto,IssueHistory.class);
        issueHistory = issueHistoryRepository.save(issueHistory);
        issueHistoryDto.setId(issueHistory.getId());
        return issueHistoryDto;
    }

    @Override
    public IssueHistoryDto getById(Long id) {
        IssueHistory history = issueHistoryRepository.findById(id).orElse(new IssueHistory());
        return modelMapper.map(history,IssueHistoryDto.class);
    }

    @Override
    public List<IssueHistoryDto> getByIssueId(Long id) {
        return Arrays.asList(modelMapper.map(issueHistoryRepository.findByIssueIdOrderById(id),IssueHistoryDto[].class));
    }

    @Override
    public TPage<IssueHistoryDto> getAllPageable(Pageable pageable) {
        Page<IssueHistory> data = issueHistoryRepository.findAll(pageable);
        TPage<IssueHistoryDto> response = new TPage<>();
        response.setStat(data,Arrays.asList(modelMapper.map(data.getContent(),IssueHistoryDto[].class)));
        return response;
    }

    @Override
    public Boolean delete(IssueHistoryDto issueHistoryDto) {
        issueHistoryRepository.delete(modelMapper.map(issueHistoryDto,IssueHistory.class));
        return Boolean.TRUE;
    }

    @Override
    public Boolean delete(Long id) {
        issueHistoryRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public void addHistory(Long id, Issue issue) {
        IssueHistory history = IssueHistory.builder()
                .issue(issue)
                .assignee(issue.getAssignee())
                .date(issue.getDate())
                .description(issue.getDescription())
                .details(issue.getDetails())
                .issueStatus(issue.getIssueStatus())
                .build();
        issueHistoryRepository.save(history);
    }
}
