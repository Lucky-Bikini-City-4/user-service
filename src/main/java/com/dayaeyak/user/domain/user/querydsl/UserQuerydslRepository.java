package com.dayaeyak.user.domain.user.querydsl;

import com.dayaeyak.user.domain.user.enums.UserRole;
import com.dayaeyak.user.domain.user.querydsl.dto.response.UserSearchProjectionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserQuerydslRepository {

    Page<UserSearchProjectionDto> searchPage(
            Pageable pageable,
            Long userId,
            String email,
            String nickname,
            UserRole role
    );
}
