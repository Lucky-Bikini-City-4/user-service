package com.dayaeyak.user.domain.user.dto.response;

import com.dayaeyak.user.domain.user.querydsl.dto.response.UserSearchProjectionDto;
import lombok.Builder;
import org.springframework.data.domain.Page;

import java.util.List;

@Builder
public record UserSearchPageResponseDto(
        int pageNumber,

        int pageSize,

        long totalElements,

        int totalPages,

        boolean first,

        boolean last,

        List<UserSearchResponseDto> data
) {

    public static UserSearchPageResponseDto from(Page<UserSearchProjectionDto> page) {
        List<UserSearchResponseDto> data = page.getContent().stream()
                .map(UserSearchResponseDto::from)
                .toList();

        return UserSearchPageResponseDto.builder()
                .pageNumber(page.getNumber())
                .pageSize(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .first(page.isFirst())
                .last(page.isLast())
                .data(data)
                .build();
    }
}
