package com.mulmeong.utility.adapter.out.infrastructure.mongo.repository.likesRepository;

import com.mulmeong.utility.adapter.out.infrastructure.mongo.entity.LikesEntity;
import com.mulmeong.utility.adapter.out.infrastructure.mongo.mapper.LikesEntityMapper;
import com.mulmeong.utility.application.port.in.dto.LikesListRequestDto;
import com.mulmeong.utility.application.port.in.dto.LikesRequestDto;
import com.mulmeong.utility.application.port.out.LikesPort;
import com.mulmeong.utility.application.port.out.dto.LikesEntityResponseDto;
import com.mulmeong.utility.application.port.out.dto.LikesResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
@Slf4j
public class LikesRepository implements LikesPort {

    private final LikesMongoRepository likesMongoRepository;
    private final LikesEntityMapper likesEntityMapper;

    @Override
    public void saveLikes(LikesResponseDto likesResponseDto) {
        likesMongoRepository.save(likesEntityMapper.toEntity(likesResponseDto));
    }

    @Override
    public void updateLikes(LikesEntityResponseDto likesEntityResponseDto) {
        likesMongoRepository.save(likesEntityMapper.toUpdate(likesEntityResponseDto));
    }

    @Override
    public Optional<LikesEntityResponseDto> findByMemberAndKind(LikesRequestDto likesRequestDto) {
        LikesEntity entity = likesMongoRepository.findByMemberUuidAndKindAndKindUuid(
                likesRequestDto.getMemberUuid(),
                likesRequestDto.getKind(),
                likesRequestDto.getKindUuid()
        );

        if (entity == null) {
            return Optional.empty();
        }

        return Optional.of(likesEntityMapper.toDto(entity));
    }

    @Override
    public List<LikesEntityResponseDto> getByMemberAndKind(LikesListRequestDto likesListRequestDto) {
        List<LikesEntity> entities = likesMongoRepository.findByMemberUuidAndKindAndStatus(
                likesListRequestDto.getMemberUuid(),
                likesListRequestDto.getKind(),
                true
        );

        return entities.stream()
                .map(likesEntityMapper::toDto)
                .collect(Collectors.toList());
    }


}
