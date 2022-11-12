package com.smartpro.dto;

import com.smartpro.enums.IssueStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IssueDto {
    private Long id;
    private String details;
    private Date date;
    private IssueStatusEnum issueStatus;
    private UserDto assignee;
    private ProjectDto project;
    private Long projectId;
}
