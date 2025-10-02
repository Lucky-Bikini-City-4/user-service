package com.dayaeyak.user.domain.user;

import com.dayaeyak.user.common.dto.Passport;
import com.dayaeyak.user.common.exception.CustomRuntimeException;
import com.dayaeyak.user.common.exception.type.UserExceptionType;
import com.dayaeyak.user.domain.user.dto.request.UserUpdateRequestDto;
import com.dayaeyak.user.domain.user.dto.response.UserFindMyPageResponseDto;
import com.dayaeyak.user.domain.user.dto.response.UserUpdateResponseDto;
import com.dayaeyak.user.domain.user.jpa.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserJpaRepository userJpaRepository;

    public UserFindMyPageResponseDto findMyPage(Passport passport) {
        User user = findUserById(passport.userId());

        return UserFindMyPageResponseDto.from(user);
    }

    @Transactional
    public UserUpdateResponseDto updateMyPage(UserUpdateRequestDto dto, Passport passport) {
        User user = findUserById(passport.userId());
        user.update(dto);

        return UserUpdateResponseDto.from(user);
    }

    private User findUserById(Long userId) {
        return userJpaRepository.findById(userId)
                .orElseThrow(() -> new CustomRuntimeException(UserExceptionType.USER_NOT_FOUND));
    }
}
