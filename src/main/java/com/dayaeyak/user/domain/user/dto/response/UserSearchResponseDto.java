package com.dayaeyak.user.domain.user.dto.response;

import com.dayaeyak.user.domain.user.enums.UserRole;
import com.dayaeyak.user.domain.user.querydsl.dto.response.UserSearchProjectionDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UserSearchResponseDto(
        Long userId,

        UserRole role,

        String email,

        String nickname,

        @JsonFormat(pattern = "yyyy년 MM월 dd일 HH:mm")
        LocalDateTime createdAt,

        @JsonFormat(pattern = "yyyy년 MM월 dd일 HH:mm")
        LocalDateTime deletedAt
) {

    public static UserSearchResponseDto from(UserSearchProjectionDto dto) {
        return UserSearchResponseDto.builder()
                .userId(dto.userId())
                .role(dto.role())
                .email(dto.email())
                .nickname(dto.nickname())
                .createdAt(dto.createdAt())
                .deletedAt(dto.deletedAt())
                .build();
    }
}
