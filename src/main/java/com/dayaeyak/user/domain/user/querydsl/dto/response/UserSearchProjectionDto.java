package com.dayaeyak.user.domain.user.querydsl.dto.response;

import com.dayaeyak.user.domain.user.enums.UserRole;
import com.querydsl.core.annotations.QueryProjection;

import java.time.LocalDateTime;

public record UserSearchProjectionDto(
        Long userId,

        UserRole role,

        String email,

        String nickname,

        LocalDateTime createdAt,

        LocalDateTime deletedAt
) {

    @QueryProjection
    public UserSearchProjectionDto {
    }
}
