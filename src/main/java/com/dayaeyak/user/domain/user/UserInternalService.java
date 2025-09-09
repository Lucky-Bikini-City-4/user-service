package com.dayaeyak.user.domain.user;

import com.dayaeyak.user.common.exception.CustomInternalException;
import com.dayaeyak.user.common.exception.type.UserExceptionType;
import com.dayaeyak.user.domain.user.dto.request.UserCreateRequestDto;
import com.dayaeyak.user.domain.user.dto.request.UserFindByEmailRequestDto;
import com.dayaeyak.user.domain.user.dto.response.UserCreateResponseDto;
import com.dayaeyak.user.domain.user.dto.response.UserFindByEmailResponseDto;
import com.dayaeyak.user.domain.user.jpa.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInternalService {

    private final UserJpaRepository userJpaRepository;

    public UserCreateResponseDto createUser(UserCreateRequestDto dto) {
        if (userJpaRepository.existsByEmail(dto.email())) {
            throw new CustomInternalException(UserExceptionType.DUPLICATED_EMAIL);
        }

        if (userJpaRepository.existsByNickname(dto.nickname())) {
            throw new CustomInternalException(UserExceptionType.DUPLICATED_NICKNAME);
        }

        User user = User.builder()
                .email(dto.email())
                .password(dto.password())
                .nickname(dto.nickname())
                .phone(dto.phone())
                .age(dto.age())
                .role(dto.role())
                .build();

        userJpaRepository.save(user);

        return UserCreateResponseDto.from(user);
    }

    public UserFindByEmailResponseDto findUserByEmail(UserFindByEmailRequestDto dto) {
        User user = findUserByEmail(dto.email());

        return UserFindByEmailResponseDto.from(user);
    }

    private User findUserByEmail(String email) {
        return userJpaRepository.findByEmail(email)
                .orElseThrow(() -> new CustomInternalException(UserExceptionType.USER_NOT_FOUND));
    }
}
