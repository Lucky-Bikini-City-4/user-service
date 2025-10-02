package com.dayaeyak.user.domain.user.dto.request;

import com.dayaeyak.user.domain.user.enums.UserRole;

public record UserAdminUpdateRequestDto(
        String nickname,

        UserRole role
) {
}
