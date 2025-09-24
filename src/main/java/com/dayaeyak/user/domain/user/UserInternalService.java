package com.dayaeyak.user.domain.user;

import com.dayaeyak.user.common.exception.CustomInternalException;
import com.dayaeyak.user.common.exception.type.UserExceptionType;
import com.dayaeyak.user.domain.user.dto.request.UserCreateRequestDto;
import com.dayaeyak.user.domain.user.dto.request.UserFindByEmailRequestDto;
import com.dayaeyak.user.domain.user.dto.request.UserSocialLoginRequestDto;
import com.dayaeyak.user.domain.user.dto.request.UserSocialSignupRequestDto;
import com.dayaeyak.user.domain.user.dto.response.*;
import com.dayaeyak.user.domain.user.jpa.UserJpaRepository;
import com.dayaeyak.user.domain.user.jpa.UserSocialJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserInternalService {

    private final UserJpaRepository userJpaRepository;
    private final UserSocialJpaRepository userSocialJpaRepository;

    public UserCreateResponseDto createUser(UserCreateRequestDto dto) {
        if (userJpaRepository.existsByEmail(dto.email())) {
            throw new CustomInternalException(UserExceptionType.DUPLICATED_EMAIL);
        }

        if (userJpaRepository.existsByNickname(dto.nickname())) {
            throw new CustomInternalException(UserExceptionType.DUPLICATED_NICKNAME);
        }

        if (userJpaRepository.existsByPhone(dto.phone())) {
            throw new CustomInternalException(UserExceptionType.DUPLICATED_PHONE);
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

    public UserSocialLoginResponseDto socialLogin(UserSocialLoginRequestDto dto) {
        // 소셜 서비스에 저장된 email로 유저 테이블 조회
        Optional<User> optionalUser = userJpaRepository.findByEmail(dto.email());

        // case 계정 존재 X
        // 소셜 회원 가입(추가 정보)
        if (optionalUser.isEmpty()) {
            return UserSocialLoginResponseDto.joinRequired();
        }

        // case 계정 존재 O
        User user = optionalUser.get();

        // 만약 소셜 정보가 없다면 새로 등록
        if (!userSocialJpaRepository.existsByUserAndProviderTypeAndProviderId(user, dto.providerType(), dto.providerId())) {
            connectSocialWithUser(user, dto);
        }

        // 로그인 완료
        return UserSocialLoginResponseDto.success(user.getId(), user.getRole());
    }

    @Transactional
    public UserSocialSignupResponseDto socialSignup(UserSocialSignupRequestDto dto) {
        if (userJpaRepository.existsByNickname(dto.nickname())) {
            throw new CustomInternalException(UserExceptionType.DUPLICATED_NICKNAME);
        }

        if (userJpaRepository.existsByPhone(dto.phone())) {
            throw new CustomInternalException(UserExceptionType.DUPLICATED_PHONE);
        }

        User user = User.builder()
                .email(dto.providerEmail())
                .nickname(dto.nickname())
                .phone(dto.phone())
                .age(dto.age())
                .role(dto.role())
                .build();

        User savedUser = userJpaRepository.save(user);

        UserSocial userSocial = new UserSocial(user, dto.providerType(), dto.providerId());

        userSocialJpaRepository.save(userSocial);

        return UserSocialSignupResponseDto.from(savedUser);
    }

    private void connectSocialWithUser(User user, UserSocialLoginRequestDto dto) {
        UserSocial userSocial = new UserSocial(user, dto.providerType(), dto.providerId());
        userSocialJpaRepository.save(userSocial);
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
