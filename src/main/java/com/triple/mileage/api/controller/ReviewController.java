package com.triple.mileage.api.controller;

import com.triple.mileage.api.domain.Review;
import com.triple.mileage.api.dto.ReviewDto;
import com.triple.mileage.api.service.ReviewService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

//    "type": "REVIEW",
//            "action": "ADD", /* "MOD", "DELETE" */
//            "reviewId": "240a0658-dc5f-4878-9381-ebb7b2667772",
//            "content": " !",
//            "attachedPhotoIds": ["e4d1a64e-a531-46de-88d0-ff0ed70c0bb8", "afb0cef2-
//            851d-4a50-bb07-9cc15cbdc332"],
//            "userId": "3ede0ef2-92b7-4817-a5f3-0c575361f745",
//            "placeId": "2e4baf1c-5acb-4efb-a1af-eddada31b00f"



    @PostMapping("/review")
    @ResponseStatus(HttpStatus.CREATED)
    public Review saveReview(@RequestBody @Valid ReviewDto.ReviewRequest dto) {
        Review review = new Review();

        switch (dto.getAction()) {
            case "ADD" :
                review = reviewService.saveReview(dto);
                break;
        }
        return review;
    }
}
