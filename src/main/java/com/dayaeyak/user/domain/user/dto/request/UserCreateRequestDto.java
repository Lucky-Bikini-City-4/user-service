package com.dayaeyak.user.domain.user.dto.request;

import com.dayaeyak.user.domain.user.enums.UserRole;

public record UserCreateRequestDto(
        String email,

        String password,

        String nickname,

        Integer age,

        String phone,

        UserRole role
) {
}
