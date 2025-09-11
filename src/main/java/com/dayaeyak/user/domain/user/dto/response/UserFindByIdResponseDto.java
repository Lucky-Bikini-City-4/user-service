package com.dayaeyak.user.domain.user.dto.response;

import com.dayaeyak.user.domain.user.User;
import com.dayaeyak.user.domain.user.enums.UserRole;

public record UserFindByIdResponseDto(
        Long userId,

        UserRole role
) {

    public static UserFindByIdResponseDto from(User user) {
        return new UserFindByIdResponseDto(user.getId(), user.getRole());
    }
}
