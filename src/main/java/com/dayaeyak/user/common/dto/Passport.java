package com.dayaeyak.user.common.dto;

import com.dayaeyak.user.domain.user.enums.UserRole;

public record Passport(
        Long userId,

        UserRole role
) {
}
