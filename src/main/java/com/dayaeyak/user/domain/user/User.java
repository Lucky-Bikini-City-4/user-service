package com.dayaeyak.user.domain.user;

import com.dayaeyak.user.common.entity.BaseEntity;
import com.dayaeyak.user.domain.user.enums.UserRole;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, unique = true, length = 10)
    private String nickname;

    @Column(nullable = false, length = 15)
    private String phone;

    @Column(nullable = false, columnDefinition = "TINYINT")
    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    UserRole role;

    @Builder
    public User(String email, String password, String nickname, String phone, Integer age, UserRole role) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.phone = phone;
        this.age = age;
        this.role = role;
    }
}
