package com.triple.mileage.api.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ReviewController {

//    "type": "REVIEW",
//            "action": "ADD", /* "MOD", "DELETE" */
//            "reviewId": "240a0658-dc5f-4878-9381-ebb7b2667772",
//            "content": " !",
//            "attachedPhotoIds": ["e4d1a64e-a531-46de-88d0-ff0ed70c0bb8", "afb0cef2-
//            851d-4a50-bb07-9cc15cbdc332"],
//            "userId": "3ede0ef2-92b7-4817-a5f3-0c575361f745",
//            "placeId": "2e4baf1c-5acb-4efb-a1af-eddada31b00f"

    @Data
    static class CreateReviewRequest {
        private String action;
        private UUID reviewId;
        private String content;
        private ArrayList<UUID> attachedPhotoIds;
        private UUID userId;
        private UUID placeId;
    }

//    @PostMapping("/review")
//    public ResponseEntity saveMember(@RequestBody @Valid CreateReviewRequest request) {
//
//        Review review = new Review(); // placeId, reviewId, 자동 생성
//
//    }
}
