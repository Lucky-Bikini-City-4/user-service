package com.dayaeyak.user.domain.user.enums;

import com.dayaeyak.user.common.exception.CustomRuntimeException;
import com.dayaeyak.user.common.exception.type.UserExceptionType;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum UserRole {

    MASTER("MASTER"),
    SELLER("SELLER"),
    NORMAL("NORMAL"),
    ;

    private final String role;

    @JsonCreator
    public static UserRole of(String role) {
        return Stream.of(UserRole.values())
                .filter(userRole -> userRole.role.equalsIgnoreCase(role))
                .findFirst()
                .orElseThrow(() -> new CustomRuntimeException(UserExceptionType.INVALID_USER_ROLE));
    }
}
