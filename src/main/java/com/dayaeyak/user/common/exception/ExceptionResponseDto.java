package com.dayaeyak.user.common.exception;

import org.springframework.http.HttpStatus;

public record ExceptionResponseDto(
        HttpStatus status,

        String message
) {
}
