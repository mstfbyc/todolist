package com.smartpro.entity;

import com.smartpro.enums.IssueStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="issue")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Issue extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "details", length = 4000)
    private String details;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "issue_status")
    @Enumerated(EnumType.STRING)
    private IssueStatusEnum issueStatus;

    @JoinColumn(name = "assignee_user_id")
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private Users assignee;

    @JoinColumn(name= "project_id")
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private Project project;

}
