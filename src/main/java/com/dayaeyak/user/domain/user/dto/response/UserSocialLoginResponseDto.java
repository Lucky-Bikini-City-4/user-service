package com.dayaeyak.user.domain.user.dto.response;


import com.dayaeyak.user.domain.user.enums.SocialLoginFlag;
import com.dayaeyak.user.domain.user.enums.UserRole;

public record UserSocialLoginResponseDto(
        SocialLoginFlag flag,

        Long userId,

        UserRole role
) {

    public static UserSocialLoginResponseDto success(Long id, UserRole role) {
        return new UserSocialLoginResponseDto(SocialLoginFlag.SUCCESS, id, role);
    }

    public static UserSocialLoginResponseDto joinRequired() {
        return new UserSocialLoginResponseDto(SocialLoginFlag.JOIN_REQUIRED, null, null);
    }
}
