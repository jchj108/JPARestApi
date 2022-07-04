package com.triple.mileage.api.service;

import com.triple.mileage.api.controller.ReviewController;
import com.triple.mileage.api.domain.*;
import com.triple.mileage.api.dto.ReviewDto;
import com.triple.mileage.api.repository.*;
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
    private final ReviewPhotoRepository reviewPhotoRepository;



    public Review saveReview(ReviewDto.ReviewRequest dto) {
        User user = userService.findOne(dto.getUserId());
        Place place = placeService.findOne(dto.getPlaceId());
        validateReviewByUserId(user.getId(), place.getId());

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
        review.updatePlaceReviewCount(place, 1);

        MileageHistory mileageHistory = MileageHistory.createMileageHistory(user, point, MileageType.WRITE_REVIEW, review);
        mileageHistoryRepository.save(mileageHistory);
        reviewRepository.save(review);

        return review;
    }

    public Review modReview(ReviewDto.ReviewRequest dto) {

        Review review = findOne(dto.getReviewId());
        Place place = placeService.findOne(dto.getPlaceId());
        User user = userService.findOne(dto.getUserId());

        if (!place.getId().equals(dto.getPlaceId())) {
            throw new IllegalStateException("장소는 수정할 수 없습니다");
        }

        long point = 0;
        MileageType mileageType = null;

        if (review.getAttachedPhotoList().size() == 0 && dto.getAttachedPhotoIds().get(0) != null) {
            point++;
            mileageType = MileageType.ADD_PHOTO;
        } else if (dto.getAttachedPhotoIds().get(0) == null && review.getAttachedPhotoList().size() != 0) {
            point--;
            mileageType = MileageType.DELETE_PHOTO;
        }
        if (point != 0) {
           mileageHistoryRepository.save(MileageHistory.createMileageHistory(user, user.getPoint() + point, mileageType, review));
        }

        reviewPhotoRepository.deleteByReviewId(review);
        List<ReviewPhoto> reviewPhotoList = ReviewPhoto.createReviewPhoto(dto.getAttachedPhotoIds());

        return review.updateReview(review, reviewPhotoList, dto, point);
    }

    public Review deleteReview(ReviewDto.ReviewRequest dto) {

        Review review = findOne(dto.getReviewId());
        User user = userRepository.findOne(dto.getUserId());
        Place place = placeRepository.findOne(dto.getPlaceId());

        Long prevPoint = mileageHistoryRepository.findByReviewId(review.getId()).getPoint();
        review.updatePoint(prevPoint * -1);
        review.updatePlaceReviewCount(place, -1);

        MileageHistory mileageHistory = MileageHistory.createMileageHistory(user, prevPoint * -1, MileageType.DELETE_REVIEW, review);
        mileageHistoryRepository.save(mileageHistory);

        review.setIsDeleted(1);

        return review;
    }

    private void validateReviewByUserId(UUID userId, UUID placeId) {
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
