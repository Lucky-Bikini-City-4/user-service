package com.dayaeyak.user.domain.user;

import com.dayaeyak.user.common.constraints.UserResponseMessage;
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
            @RequestHeader("X-User-Id") Long userId
    ) {
        UserFindMyPageResponseDto data = userService.findMyPage(userId);

        return ApiResponse.success(HttpStatus.OK, UserResponseMessage.GET_MY_PAGE_SUCCESS, data);
    }

    @PatchMapping("/my")
    public ResponseEntity<ApiResponse<UserUpdateResponseDto>> updateMyPage(
            @RequestBody @Valid UserUpdateRequestDto userUpdateRequestDto,
            @RequestHeader("X-User-Id") Long userId
    ) {
        UserUpdateResponseDto data = userService.updateMyPage(userUpdateRequestDto, userId);

        return ApiResponse.success(HttpStatus.OK, UserResponseMessage.UPDATE_MY_PAGE_SUCCESS, data);
    }
}
