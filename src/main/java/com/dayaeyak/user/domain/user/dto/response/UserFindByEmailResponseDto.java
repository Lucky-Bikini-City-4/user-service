package com.dayaeyak.user.domain.user.dto.response;

import com.dayaeyak.user.domain.user.User;
import com.dayaeyak.user.domain.user.enums.UserRole;

public record UserFindByEmailResponseDto(
        Long userId,

        String password,

        UserRole role
) {

    public static UserFindByEmailResponseDto from(User user) {
        return new UserFindByEmailResponseDto(user.getId(), user.getPassword(), user.getRole());
    }
}
