package com.dayaeyak.user.domain.user.dto.response;

import com.dayaeyak.user.domain.user.User;
import com.dayaeyak.user.domain.user.enums.UserRole;

public record UserSocialSignupResponseDto(
        Long userId,

        UserRole role
) {

    public static UserSocialSignupResponseDto from(User user) {
        return new UserSocialSignupResponseDto(user.getId(), user.getRole());
    }
}
