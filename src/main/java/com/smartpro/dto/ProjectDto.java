package com.smartpro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {
    private Long id;
    @NotNull(message = "Project Name Not Null")
    private String projectName;
    @NotNull(message = "Project Code Not Null")
    private String projectCode;
    private Long managerId;
    private UserDto manager;
}
