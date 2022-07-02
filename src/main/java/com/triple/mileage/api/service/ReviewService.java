package com.triple.mileage.api.service;

import com.triple.mileage.api.controller.ReviewController;
import com.triple.mileage.api.domain.Place;
import com.triple.mileage.api.domain.Review;
import com.triple.mileage.api.domain.ReviewPhoto;
import com.triple.mileage.api.domain.User;
import com.triple.mileage.api.dto.ReviewDto;
import com.triple.mileage.api.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewService {

    private final UserService userService;
    private final PlaceService placeService;
    private final ReviewRepository reviewRepository;

    public Review saveReview(ReviewDto.ReviewRequest dto) {
        User user = userService.findOne(dto.getUserId());
        Place place = placeService.findOne(dto.getPlaceId());

        List<ReviewPhoto> reviewPhoto = ReviewPhoto.createReviewPhoto(dto.getAttachedPhotoIds());

        Review review = Review.createReview(user, place, reviewPhoto);

        return review;
    }
}
