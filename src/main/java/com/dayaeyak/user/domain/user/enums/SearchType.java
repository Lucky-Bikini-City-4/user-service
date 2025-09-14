package com.dayaeyak.user.domain.user.enums;

import com.dayaeyak.user.domain.user.QUser;
import com.querydsl.core.types.dsl.StringPath;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SearchType {

    EMAIL("email", QUser.user.email),
    NICKNAME("nickname", QUser.user.nickname),
    ;

    private final String type;
    private final StringPath path;
}
