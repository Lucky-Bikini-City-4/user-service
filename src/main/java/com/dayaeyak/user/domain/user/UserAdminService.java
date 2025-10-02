package com.dayaeyak.user.domain.user;

import com.dayaeyak.user.common.exception.CustomInternalException;
import com.dayaeyak.user.common.exception.type.UserExceptionType;
import com.dayaeyak.user.domain.user.dto.request.UserAdminUpdateRequestDto;
import com.dayaeyak.user.domain.user.dto.response.UserSearchPageResponseDto;
import com.dayaeyak.user.domain.user.dto.response.UserUpdateResponseDto;
import com.dayaeyak.user.domain.user.enums.SearchType;
import com.dayaeyak.user.domain.user.enums.UserRole;
import com.dayaeyak.user.domain.user.jpa.UserJpaRepository;
import com.dayaeyak.user.domain.user.querydsl.UserQuerydslRepository;
import com.dayaeyak.user.domain.user.querydsl.dto.response.UserSearchProjectionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserAdminService {

    private final UserJpaRepository userJpaRepository;
    private final UserQuerydslRepository userQuerydslRepository;

    public UserSearchPageResponseDto searchUser(
            int page,
            int size,
            Long userId,
            UserRole role,
            String keyword,
            SearchType searchType
    ) {
        Pageable pageable = PageRequest.of(page, size);

        Page<UserSearchProjectionDto> data
                = userQuerydslRepository.searchPage(pageable, userId, role, keyword, searchType);

        return UserSearchPageResponseDto.from(data);
    }

    @Transactional
    public UserUpdateResponseDto updateUser(Long userId, UserAdminUpdateRequestDto dto) {
        User user = findById(userId);
        user.updateByAdmin(dto);

        return UserUpdateResponseDto.from(user);
    }

    private User findById(Long userId) {
        return userJpaRepository.findById(userId)
                .orElseThrow(() -> new CustomInternalException(UserExceptionType.USER_NOT_FOUND));
    }
}
