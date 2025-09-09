package com.dayaeyak.user.domain.user.dto.response;

import com.dayaeyak.user.domain.user.User;
import com.dayaeyak.user.domain.user.enums.UserRole;

public record UserCreateResponseDto(
        Long userId,

        UserRole role
) {

    public static UserCreateResponseDto from(User user) {
        return new UserCreateResponseDto(user.getId(), user.getRole());
    }
}
