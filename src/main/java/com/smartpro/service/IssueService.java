package com.smartpro.service;

import com.smartpro.dto.IssueDto;

public interface IssueService {
    IssueDto save(IssueDto issue);
    IssueDto getById(Long id);
    Boolean delete(Long issue);
    IssueDto update(Long id, IssueDto project);
}
