package com.dayaeyak.user.common.exception.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserExceptionType implements ExceptionType {

    DUPLICATED_EMAIL(HttpStatus.CONFLICT, "이미 존재하는 이메일입니다."),
    DUPLICATED_NICKNAME(HttpStatus.CONFLICT,"이미 존재하는 닉네임입니다."),
    DUPLICATED_PHONE(HttpStatus.CONFLICT,"이미 존재하는 휴대폰 번호입니다."),
    INVALID_USER_ROLE(HttpStatus.UNAUTHORIZED, "유효하지 않은 유저 권한입니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "유저 정보를 찾을 수 없습니다.")
    ;

    private final HttpStatus status;
    private final String message;
}
