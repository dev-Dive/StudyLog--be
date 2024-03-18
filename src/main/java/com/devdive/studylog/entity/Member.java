package com.devdive.studylog.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String nickname;

    @Column
    private String email;

    public Member(String nickname, String email) {
        this.nickname = nickname;
        this.email = email;
    }
}
