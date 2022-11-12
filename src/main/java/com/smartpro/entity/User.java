package com.smartpro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name= "users", indexes = {@Index(name = "idx_username", columnList = "uname")})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="uname", length = 100, unique = true)
    private String username;

    @Column(name = "pwd", length = 200)
    private String password;

    @Column(name = "email", length = 100)
    private String email;

    @JoinColumn(name = "assignee_user_id")
    @OneToMany( fetch = FetchType.LAZY)
    private List<Issue> issue;
}
