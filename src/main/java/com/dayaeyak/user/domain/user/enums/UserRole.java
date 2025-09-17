package com.dayaeyak.user.domain.user.enums;

import com.dayaeyak.user.common.exception.CustomRuntimeException;
import com.dayaeyak.user.common.exception.type.CommonExceptionType;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.stream.Stream;

public enum UserRole {

    MASTER,
    SELLER,
    NORMAL,
    ;

    @JsonCreator
    public static UserRole of(String role) {
        return Stream.of(UserRole.values())
                .filter(userRole -> userRole.name().equalsIgnoreCase(role))
                .findFirst()
                .orElseThrow(() -> new CustomRuntimeException(CommonExceptionType.INVALID_USER_ROLE));
    }
}
