package com.triple.mileage.api.dto;

import com.triple.mileage.api.domain.Review;
import com.triple.mileage.api.domain.ReviewAction;
import com.triple.mileage.api.domain.ReviewPhoto;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ReviewDto {

    @Data
    public static class ReviewRequest {
        private ReviewAction action;
        private UUID reviewId;
        private String content;
        private List<UUID> attachedPhotoIds;
        private UUID userId;
        private UUID placeId;
    }

    @Getter
    public static class Res {
        private UUID reviewId;
        private String content;
        private List<UUID> attachedPhotoIds;
        private UUID userId;
        private UUID placeId;

        public Res(Review review) {
            this.reviewId = review.getId();
            this.content = review.getContent();
            this.attachedPhotoIds = new ArrayList<>();
            for (ReviewPhoto reviewPhoto : review.getAttachedPhotoList()) {
                this.attachedPhotoIds.add(reviewPhoto.getId());
            }
            this.userId = review.getUser().getId();
            this.placeId = review.getPlace().getId();
        }
    }
}
