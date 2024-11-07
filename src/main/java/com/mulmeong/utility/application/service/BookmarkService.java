package com.mulmeong.utility.application.service;

import com.mulmeong.utility.application.mapper.BookmarkDtoMapper;
import com.mulmeong.utility.application.port.in.BookmarkUseCase;
import com.mulmeong.utility.application.port.in.dto.BookmarkRequestDto;
import com.mulmeong.utility.application.port.out.BookmarkPort;
import com.mulmeong.utility.domain.model.Bookmark;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookmarkService implements BookmarkUseCase {
    private final BookmarkPort bookmarkPort;
    private final BookmarkDtoMapper bookmarkDtoMapper;

    @Override
    public void addBookmark(BookmarkRequestDto requestDto) {
        Bookmark newBookmark = Bookmark.builder()
                .memberUuid(requestDto.getMemberUuid())
                .bookmarkUuid(requestDto.getFeedUuid())
                .build();

        bookmarkPort.addBookmark(bookmarkDtoMapper.toDto(newBookmark));
    }
}