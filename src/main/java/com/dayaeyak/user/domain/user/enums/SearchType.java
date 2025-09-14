package com.dayaeyak.user.domain.user.enums;

import com.dayaeyak.user.common.exception.CustomRuntimeException;
import com.dayaeyak.user.common.exception.type.CommonExceptionType;
import com.dayaeyak.user.common.exception.type.UserExceptionType;
import com.dayaeyak.user.domain.user.QUser;
import com.querydsl.core.types.dsl.StringPath;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum SearchType {

    EMAIL("email", QUser.user.email),
    NICKNAME("nickname", QUser.user.nickname),
    ;

    private final String type;
    private final StringPath path;

    public static SearchType of(String type) {
        return Arrays.stream(SearchType.values())
                .filter(searchType -> searchType.type.equalsIgnoreCase(type))
                .findFirst()
                .orElseThrow(() -> new CustomRuntimeException(UserExceptionType.INVALID_SEARCH_TYPE));
    }
}
