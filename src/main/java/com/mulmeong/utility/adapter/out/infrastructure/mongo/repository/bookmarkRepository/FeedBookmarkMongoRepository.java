package com.mulmeong.utility.adapter.out.infrastructure.mongo.repository.bookmarkRepository;

import com.mulmeong.utility.adapter.out.infrastructure.mongo.entity.FeedBookmarkEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface FeedBookmarkMongoRepository extends MongoRepository<FeedBookmarkEntity, String> {

    boolean existsByMemberUuidAndFeedUuid(String memberUuid, String bookmarkUuid);

    void deleteByMemberUuidAndFeedUuid(String memberUuid, String feedUuid);

    List<FeedBookmarkEntity> findByMemberUuidAndIdLessThanOrderByIdDesc(
            String memberUuid, String lastId, Pageable pageable);

    List<FeedBookmarkEntity> findAllByMemberUuidOrderByIdDesc(String memberUuid, Pageable pageable);
}
