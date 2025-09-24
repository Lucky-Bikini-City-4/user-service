package com.dayaeyak.user.domain.user.dto.request;

import com.dayaeyak.user.domain.user.enums.ProviderType;
import com.dayaeyak.user.domain.user.enums.UserRole;
import lombok.Builder;

@Builder
public record UserSocialSignupRequestDto(
        String nickname,

        Integer age,

        String phone,

        UserRole role,

        ProviderType providerType,

        String providerId,

        String providerEmail
) {
}
