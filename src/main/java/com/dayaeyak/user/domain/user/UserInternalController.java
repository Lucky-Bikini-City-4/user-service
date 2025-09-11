package com.dayaeyak.user.domain.user;

import com.dayaeyak.user.domain.user.dto.request.UserCreateRequestDto;
import com.dayaeyak.user.domain.user.dto.request.UserFindByEmailRequestDto;
import com.dayaeyak.user.domain.user.dto.response.UserCreateResponseDto;
import com.dayaeyak.user.domain.user.dto.response.UserFindByEmailResponseDto;
import com.dayaeyak.user.domain.user.dto.response.UserFindByIdResponseDto;
import com.dayaeyak.user.domain.user.dto.response.UserSearchPageResponseDto;
import com.dayaeyak.user.domain.user.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/internal/users")
@RequiredArgsConstructor
public class UserInternalController {

    private final UserInternalService userInternalService;

    @PostMapping("/create-user")
    public UserCreateResponseDto createUser(
            @RequestBody UserCreateRequestDto userCreateRequestDto
    ) {
        return userInternalService.createUser(userCreateRequestDto);
    }

    @PostMapping("/find-user")
    public UserFindByEmailResponseDto findUser(
            @RequestBody UserFindByEmailRequestDto userFindByEmailRequestDto
    ) {
        return userInternalService.findUserByEmail(userFindByEmailRequestDto);
    }

    @GetMapping("/{userId}")
    public UserFindByIdResponseDto getUser(
            @PathVariable Long userId
    ) {
        return userInternalService.findUserById(userId);
    }

    @GetMapping
    public UserSearchPageResponseDto searchUser(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String nickname,
            @RequestParam(required = false) UserRole role
    ) {
        return userInternalService.searchUser(page, size, userId, email, nickname, role);
    }
}
