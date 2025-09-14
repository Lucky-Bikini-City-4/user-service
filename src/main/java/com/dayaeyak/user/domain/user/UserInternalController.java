package com.dayaeyak.user.domain.user;

import com.dayaeyak.user.domain.user.dto.request.UserCreateRequestDto;
import com.dayaeyak.user.domain.user.dto.request.UserFindByEmailRequestDto;
import com.dayaeyak.user.domain.user.dto.response.UserCreateResponseDto;
import com.dayaeyak.user.domain.user.dto.response.UserFindByEmailResponseDto;
import com.dayaeyak.user.domain.user.dto.response.UserFindByIdResponseDto;
import lombok.RequiredArgsConstructor;
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
    public UserFindByEmailResponseDto getUser(
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
}
