package com.dayaeyak.user.domain.user.enums;

import com.dayaeyak.user.common.exception.CustomInternalException;
import com.dayaeyak.user.common.exception.type.UserExceptionType;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ProviderType {

    GOOGLE,
    KAKAO,
    ;

    @JsonCreator
    public static ProviderType of(String value) {
        return Arrays.stream(ProviderType.values())
                .filter(providerType -> providerType.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new CustomInternalException(UserExceptionType.INVALID_ACCOUNT_TYPE));
    }
}
