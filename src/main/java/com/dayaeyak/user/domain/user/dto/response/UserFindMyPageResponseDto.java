package com.dayaeyak.user.domain.user.dto.response;

import com.dayaeyak.user.domain.user.User;
import com.dayaeyak.user.domain.user.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record UserFindMyPageResponseDto(
        Long userId,

        String email,

        String nickname,

        Integer age,

        String phone,

        UserRole role,

        @JsonFormat(pattern = "yy년 MM월 dd일")
        LocalDate createdAt,

        @JsonFormat(pattern = "yy년 MM월 dd일")
        LocalDate updatedAt
) {

    public static UserFindMyPageResponseDto from(User user) {
        return UserFindMyPageResponseDto.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .age(user.getAge())
                .phone(user.getPhone())
                .role(user.getRole())
                .createdAt(user.getCreatedAt().toLocalDate())
                .updatedAt(user.getUpdatedAt().toLocalDate())
                .build();
    }
}
