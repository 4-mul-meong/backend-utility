package com.mulmeong.utility.adapter.in.web.controller;

import com.mulmeong.utility.adapter.in.web.vo.LikesRequestVo;
import com.mulmeong.utility.application.port.in.LikesUseCase;
import com.mulmeong.utility.common.response.BaseResponse;
import com.mulmeong.utility.common.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/members")
@RestController
public class LikesController {

    private final LikesUseCase likesUseCase;


    @PostMapping("/likes")
    public BaseResponse<Void> likes(@RequestBody LikesRequestVo request) {

        try {
            likesUseCase.likes(request.toDto());
            return new BaseResponse<>(BaseResponseStatus.SUCCESS);
        } catch (Exception e) {
            return new BaseResponse<>(BaseResponseStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
