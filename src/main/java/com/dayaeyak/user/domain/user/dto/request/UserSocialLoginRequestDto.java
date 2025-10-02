package com.dayaeyak.user.domain.user.dto.request;

import com.dayaeyak.user.domain.user.enums.ProviderType;

public record UserSocialLoginRequestDto(
        ProviderType providerType,

        String providerId,

        String email
) {
}
