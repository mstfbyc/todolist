package com.smartpro.dto;

import com.smartpro.entity.IssueHistory;
import com.smartpro.enums.IssueStatusEnum;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class IssueHistoryDto {
    private Long id;
    private IssueDto issue;
    private String description;
    private Date date;
    private IssueStatusEnum issueStatus;
    private String details;
    private UserDto assignee;

}
