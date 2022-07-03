package com.triple.mileage.api.service;

import com.triple.mileage.api.controller.ReviewController;
import com.triple.mileage.api.domain.*;
import com.triple.mileage.api.dto.ReviewDto;
import com.triple.mileage.api.repository.MileageHistoryRepository;
import com.triple.mileage.api.repository.PlaceRepository;
import com.triple.mileage.api.repository.ReviewRepository;
import com.triple.mileage.api.repository.UserRepository;
import com.triple.mileage.exception.OneUserCanWriteOnlyOnePlaceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final UserService userService;
    private final PlaceService placeService;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final MileageHistoryRepository mileageHistoryRepository;
    private final PlaceRepository placeRepository;



    public Review saveReview(ReviewDto.ReviewRequest dto) {
        User user = userService.findOne(dto.getUserId());
        Place place = placeService.findOne(dto.getPlaceId());
        validateReviewByUserid(user.getId(), place.getId());

        List<ReviewPhoto> reviewPhoto = ReviewPhoto.createReviewPhoto(dto.getAttachedPhotoIds());

        long point = 0;

        if (dto.getContent() != null && dto.getContent().length() >= 1) {
            point++;
        }
        if (reviewPhoto.size() > 0) {
            point++;
        }
        if (placeService.findOne(place.getId()).getReviewCount() == 0) {
            point++;
        }

        Review review = Review.createReview(user, place, reviewPhoto, dto);
        review.updatePoint(point);
        review.updatePlaceReviewCount(place);

        MileageHistory mileageHistory = MileageHistory.createMileageHistory(user, MileageType.WRITE_REVIEW);
        mileageHistoryRepository.save(mileageHistory);
        reviewRepository.save(review);

        return review;
    }

    private void validateReviewByUserid(UUID userId, UUID placeId) {
        Review review = reviewRepository.findOneByUserId(userId, placeId);
        if (review != null) {
            throw new OneUserCanWriteOnlyOnePlaceException("리뷰는 장소당 한 개만 등록할 수 있습니다");
        }
    }

    @Transactional(readOnly = true)
    public Review findOne(UUID id) {
        Review findReview = reviewRepository.findOne(id);
        if (findReview == null) {
            throw new IllegalArgumentException("리뷰가 존재하지 않습니다");
        }
        return findReview;
    }
}
