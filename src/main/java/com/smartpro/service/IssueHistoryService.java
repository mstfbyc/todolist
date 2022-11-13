package com.smartpro.service;

import com.smartpro.dto.IssueHistoryDto;
import com.smartpro.entity.Issue;
import com.smartpro.util.TPage;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IssueHistoryService {
    IssueHistoryDto save(IssueHistoryDto issueHistoryDto);
    IssueHistoryDto getById(Long id);
    List<IssueHistoryDto> getByIssueId(Long id);
    TPage<IssueHistoryDto> getAllPageable(Pageable pageable);
    Boolean delete(IssueHistoryDto issueHistoryDto);
    Boolean delete(Long id);
    void addHistory(Long id, Issue issue);
}
