package com.dayaeyak.user.domain.user.querydsl;

import com.dayaeyak.user.domain.user.User;
import com.dayaeyak.user.domain.user.enums.SearchType;
import com.dayaeyak.user.domain.user.enums.UserRole;
import com.dayaeyak.user.domain.user.querydsl.dto.response.UserSearchProjectionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserQuerydslRepository {

    Page<UserSearchProjectionDto> searchPage(
            Pageable pageable,
            Long userId,
            UserRole role,
            String keyword,
            SearchType searchType
    );

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

    boolean existsByPhone(String phone);
}
