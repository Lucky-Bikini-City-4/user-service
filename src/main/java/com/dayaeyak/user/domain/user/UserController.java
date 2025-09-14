package com.dayaeyak.user.domain.user;

import com.dayaeyak.user.common.annotation.PassportHolder;
import com.dayaeyak.user.common.constraints.UserResponseMessage;
import com.dayaeyak.user.common.dto.Passport;
import com.dayaeyak.user.common.entity.ApiResponse;
import com.dayaeyak.user.domain.user.dto.request.UserUpdateRequestDto;
import com.dayaeyak.user.domain.user.dto.response.UserFindMyPageResponseDto;
import com.dayaeyak.user.domain.user.dto.response.UserUpdateResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/my")
    public ResponseEntity<ApiResponse<UserFindMyPageResponseDto>> getMyPage(
            @PassportHolder Passport passport
    ) {
        UserFindMyPageResponseDto data = userService.findMyPage(passport);

        return ApiResponse.success(HttpStatus.OK, UserResponseMessage.GET_MY_PAGE, data);
    }

    @PatchMapping("/my")
    public ResponseEntity<ApiResponse<UserUpdateResponseDto>> updateMyPage(
            @RequestBody @Valid UserUpdateRequestDto userUpdateRequestDto,
            @PassportHolder Passport passport
    ) {
        UserUpdateResponseDto data = userService.updateMyPage(userUpdateRequestDto, passport);

        return ApiResponse.success(HttpStatus.OK, UserResponseMessage.UPDATE_MY_PAGE, data);
    }
}
