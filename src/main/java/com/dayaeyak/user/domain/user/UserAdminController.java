package com.dayaeyak.user.domain.user;

import com.dayaeyak.user.common.constant.UserResponseMessage;
import com.dayaeyak.user.common.entity.ApiResponse;
import com.dayaeyak.user.domain.user.dto.request.UserAdminUpdateRequestDto;
import com.dayaeyak.user.domain.user.dto.response.UserSearchPageResponseDto;
import com.dayaeyak.user.domain.user.dto.response.UserUpdateResponseDto;
import com.dayaeyak.user.domain.user.enums.SearchType;
import com.dayaeyak.user.domain.user.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class UserAdminController {

    private final UserAdminService userAdminService;

    @GetMapping
    public ResponseEntity<ApiResponse<UserSearchPageResponseDto>> searchUser(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) UserRole role,
            @RequestParam String keyword,
            @RequestParam SearchType searchType
    ) {
        UserSearchPageResponseDto data = userAdminService.searchUser(page, size, userId, role, keyword, searchType);

        return ApiResponse.success(HttpStatus.OK, UserResponseMessage.SEARCH, data);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserUpdateResponseDto>> updateUser(
            @PathVariable Long userId,
            @RequestBody UserAdminUpdateRequestDto userAdminUpdateRequestDto
    ) {
        UserUpdateResponseDto data = userAdminService.updateUser(userId, userAdminUpdateRequestDto);

        return ApiResponse.success(HttpStatus.OK, UserResponseMessage.UPDATE, data);
    }
}
