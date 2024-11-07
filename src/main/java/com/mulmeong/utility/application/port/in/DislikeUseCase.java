package com.mulmeong.utility.application.port.in;

import com.mulmeong.utility.application.port.in.dto.DislikeListRequestDto;
import com.mulmeong.utility.application.port.in.dto.DislikeRequestDto;

import java.util.List;

public interface DislikeUseCase {

    void dislike(DislikeRequestDto dislikeRequestDto);

    boolean isChecked(DislikeRequestDto dislikeRequestDto);

    List<String> getDislikes(DislikeListRequestDto dislikeListRequestDto);
}
