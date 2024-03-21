package com.devdive.studylog.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "members")
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String nickname;

    @Column
    private String email;

    @Column(unique = true)
    private Integer uniqueNumber;

    public Member(String nickname, String email, Integer uniqueNumber) {
        this.nickname = nickname;
        this.email = email;
        this.uniqueNumber = uniqueNumber;
    }
}
