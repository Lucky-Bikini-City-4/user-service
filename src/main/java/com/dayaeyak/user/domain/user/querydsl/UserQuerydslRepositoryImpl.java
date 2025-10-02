package com.dayaeyak.user.domain.user.querydsl;

import com.dayaeyak.user.domain.user.enums.SearchType;
import com.dayaeyak.user.domain.user.enums.UserRole;
import com.dayaeyak.user.domain.user.querydsl.dto.response.QUserSearchProjectionDto;
import com.dayaeyak.user.domain.user.querydsl.dto.response.UserSearchProjectionDto;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.dayaeyak.user.domain.user.QUser.user;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserQuerydslRepositoryImpl implements UserQuerydslRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<UserSearchProjectionDto> searchPage(
            Pageable pageable,
            Long userId,
            UserRole role,
            String keyword,
            SearchType searchType
    ) {
        Predicate[] whereClauses = {
                eqUserId(userId),
                eqSearchType(keyword, searchType),
                eqRole(role)
        };

        List<UserSearchProjectionDto> data = queryFactory.select(new QUserSearchProjectionDto(
                        user.id,
                        user.role,
                        user.email,
                        user.nickname,
                        user.createdAt,
                        user.deletedAt
                ))
                .from(user)
                .where(whereClauses)
                .orderBy(user.createdAt.desc()) // 정렬 고정
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> count = queryFactory.select(user.count())
                .from(user)
                .where(whereClauses);

        return PageableExecutionUtils.getPage(data, pageable, count::fetchOne);
    }

    private BooleanExpression eqSearchType(String keyword, SearchType searchType) {
        return searchType != null ? searchType.getPath().contains(keyword) : null;
    }

    private BooleanExpression eqUserId(Long userId) {
        return userId != null ? user.id.eq(userId) : null;
    }

    private BooleanExpression eqEmail(String email) {
        return StringUtils.hasText(email) ? user.email.contains(email) : null;
    }

    private BooleanExpression eqNickname(String nickname) {
        return StringUtils.hasText(nickname) ? user.nickname.contains(nickname) : null;
    }

    private BooleanExpression eqRole(UserRole role) {
        return role != null ? user.role.eq(role) : null;
    }
}
