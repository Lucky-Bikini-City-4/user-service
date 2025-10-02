package com.dayaeyak.user.domain.user.dto.response;

import com.dayaeyak.user.domain.user.User;
import com.dayaeyak.user.domain.user.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record UserUpdateResponseDto(
        Long userId,

        String email,

        String nickname,

        Integer age,

        String phone,

        UserRole role,

        @JsonFormat(pattern = "yy년 MM월 dd일 HH:mm")
        LocalDateTime createdAt,

        @JsonFormat(pattern = "yy년 MM월 dd일 HH:mm")
        LocalDateTime updatedAt
) {

    public static UserUpdateResponseDto from(User user) {
        return UserUpdateResponseDto.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .age(user.getAge())
                .phone(user.getPhone())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
