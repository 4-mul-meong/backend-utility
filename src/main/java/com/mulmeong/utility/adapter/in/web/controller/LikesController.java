package com.mulmeong.utility.adapter.in.web.controller;

import com.mulmeong.utility.adapter.in.web.vo.LikesRequestVo;
import com.mulmeong.utility.application.port.in.LikesUseCase;
import com.mulmeong.utility.application.port.in.dto.LikesListRequestDto;
import com.mulmeong.utility.application.port.in.dto.LikesRequestDto;
import com.mulmeong.utility.common.response.BaseResponse;
import com.mulmeong.utility.common.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/members")
@RestController
public class LikesController {

    private final LikesUseCase likesUseCase;

    // 좋아요 추가
    @PostMapping("/likes")
    public BaseResponse<Void> likes(@RequestBody LikesRequestVo request) {

        likesUseCase.likes(request.toDto());
        return new BaseResponse<>();

    }

    // 좋아요 상태 조회
    @GetMapping("/{memberUuid}/{kind}/{kindUuid}/like-status")
    public BaseResponse<Boolean> likesStatus(
            @PathVariable String memberUuid, @PathVariable String kind, @PathVariable String kindUuid) {

        return new BaseResponse<>(likesUseCase.isChecked(new LikesRequestDto(memberUuid, kind, kindUuid)));

    }

    // 좋아요 한 컨텐츠 조회
    @GetMapping("/{memberUuid}/{kind}/likes")
    public BaseResponse<List<String>> getLikes(@PathVariable String memberUuid, @PathVariable String kind) {

        return new BaseResponse<>(likesUseCase.getLikes(new LikesListRequestDto(memberUuid, kind)));

    }


}
