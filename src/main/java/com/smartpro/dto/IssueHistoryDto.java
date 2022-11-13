package com.smartpro.dto;

import com.smartpro.enums.IssueStatusEnum;
import lombok.Data;

import java.util.Date;

@Data
public class IssueHistoryDto {
    private Long id;
    private IssueDto issue;
    private String description;
    private Date date;
    private IssueStatusEnum issueStatus;
    private String details;
    private UserDto assignee;
}
