package com.dayaeyak.user.domain.user;

import com.dayaeyak.user.common.exception.CustomInternalException;
import com.dayaeyak.user.common.exception.type.UserExceptionType;
import com.dayaeyak.user.domain.user.dto.request.UserCreateRequestDto;
import com.dayaeyak.user.domain.user.dto.request.UserFindByEmailRequestDto;
import com.dayaeyak.user.domain.user.dto.response.UserCreateResponseDto;
import com.dayaeyak.user.domain.user.dto.response.UserFindByEmailResponseDto;
import com.dayaeyak.user.domain.user.dto.response.UserFindByIdResponseDto;
import com.dayaeyak.user.domain.user.dto.response.UserSearchPageResponseDto;
import com.dayaeyak.user.domain.user.enums.UserRole;
import com.dayaeyak.user.domain.user.jpa.UserJpaRepository;
import com.dayaeyak.user.domain.user.querydsl.UserQuerydslRepository;
import com.dayaeyak.user.domain.user.querydsl.dto.response.UserSearchProjectionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInternalService {

    private final UserJpaRepository userJpaRepository;
    private final UserQuerydslRepository userQuerydslRepository;

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

    public UserFindByIdResponseDto findUserById(Long userId) {
        User user = findById(userId);

        return UserFindByIdResponseDto.from(user);
    }

    public UserSearchPageResponseDto searchUser(
            int page,
            int size,
            Long userId,
            String email,
            String nickname,
            UserRole role
    ) {
        Pageable pageable = PageRequest.of(page, size);

        Page<UserSearchProjectionDto> data
                = userQuerydslRepository.searchPage(pageable, userId, email, nickname, role);

        return UserSearchPageResponseDto.from(data);
    }

    private User findUserByEmail(String email) {
        return userJpaRepository.findByEmail(email)
                .orElseThrow(() -> new CustomInternalException(UserExceptionType.USER_NOT_FOUND));
    }

    private User findById(Long userId) {
        return userJpaRepository.findById(userId)
                .orElseThrow(() -> new CustomInternalException(UserExceptionType.USER_NOT_FOUND));
    }
}
