package com.triple.mileage.api.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

public class ReviewDto {

    @Data
    public static class ReviewRequest {
        private String action;
        private UUID reviewId;
        private String content;
        private List<UUID> attachedPhotoIds;
        private UUID userId;
        private UUID placeId;
    }
}
