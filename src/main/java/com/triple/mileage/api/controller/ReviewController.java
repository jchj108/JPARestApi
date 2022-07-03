package com.triple.mileage.api.controller;

import com.triple.mileage.api.common.ErrorResponse;
import com.triple.mileage.api.domain.Review;
import com.triple.mileage.api.dto.ReviewDto;
import com.triple.mileage.api.service.ReviewService;
import com.triple.mileage.exception.OneUserCanWriteOnlyOnePlaceException;
import com.triple.mileage.exception.ReviewRequestInvalidException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @ExceptionHandler(OneUserCanWriteOnlyOnePlaceException.class)
    public ResponseEntity<ErrorResponse> handleOneUserCanWriteOnlyOnePlaceException(OneUserCanWriteOnlyOnePlaceException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
    @ExceptionHandler(ReviewRequestInvalidException.class)
    public ResponseEntity<ErrorResponse> handleReviewRequestInvalidException(ReviewRequestInvalidException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @PostMapping("/review")
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewDto.Res saveReview(@RequestBody @Valid ReviewDto.ReviewRequest dto) {
        Review review = new Review();

        switch (dto.getAction()) {
            case "ADD" :
                review = reviewService.saveReview(dto);
                break;
            case "MOD" :
                break;
            case "DELETE" :
                break;
            default: throw new ReviewRequestInvalidException("유효하지 않은 액션입니다");
        }
        return new ReviewDto.Res(review);
    }
}
