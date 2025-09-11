package com.dayaeyak.user.domain.user.dto.request;

import com.dayaeyak.user.common.constraints.UserValidationMessage;
import jakarta.validation.constraints.NotBlank;

public record UserUpdateRequestDto(
        @NotBlank(message = UserValidationMessage.INVALID_NICKNAME_MESSAGE)
//        @Size(
//                min = AuthValidationMessage.NICKNAME_MIN_LENGTH,
//                max = AuthValidationMessage.NICKNAME_MAX_LENGTH,
//                message = AuthValidationMessage.INVALID_NICKNAME_MESSAGE
//        )
        String nickname
) {
}
