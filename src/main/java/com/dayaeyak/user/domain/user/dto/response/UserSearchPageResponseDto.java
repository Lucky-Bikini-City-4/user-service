package com.dayaeyak.user.domain.user.dto.response;

import com.dayaeyak.user.common.dto.PageInfoResponseDto;
import com.dayaeyak.user.domain.user.querydsl.dto.response.UserSearchProjectionDto;
import lombok.Builder;
import org.springframework.data.domain.Page;

import java.util.List;

@Builder
public record UserSearchPageResponseDto(
        PageInfoResponseDto pageInfo,

        List<UserSearchResponseDto> data
) {

    public static UserSearchPageResponseDto from(Page<UserSearchProjectionDto> page) {
        PageInfoResponseDto pageInfo = PageInfoResponseDto.from(page);

        List<UserSearchResponseDto> data = page.getContent().stream()
                .map(UserSearchResponseDto::from)
                .toList();

        return UserSearchPageResponseDto.builder()
                .pageInfo(pageInfo)
                .data(data)
                .build();
    }
}
