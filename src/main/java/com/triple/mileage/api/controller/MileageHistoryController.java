package com.triple.mileage.api.controller;

import com.triple.mileage.api.common.ErrorResponse;
import com.triple.mileage.api.domain.MileageHistory;
import com.triple.mileage.api.dto.MileageHistoryDto;
import com.triple.mileage.api.repository.MileageHistoryRepository;
import com.triple.mileage.api.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MileageHistoryController {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    private final MileageHistoryRepository mileageHistoryRepository;
    private final UserService userService;

    @GetMapping(path = "mileage-histories/{userId}")
    public ResponseEntity findHistoriesByUser(@PathVariable("userId") UUID userId) {

        userService.findOne(userId);
        List<MileageHistory> mileageHistoryList = mileageHistoryRepository.findHistoriesByUser(userId);
        List<MileageHistoryDto.Res> resList = new ArrayList<>();

        for(MileageHistory mileageHistory : mileageHistoryList) {
            resList.add(new MileageHistoryDto.Res(mileageHistory));
        }

        return ResponseEntity.status(HttpStatus.OK).body(resList);
    }

    @GetMapping("/mileageHistories")
    public ResponseEntity findHistories() {

        List<MileageHistory> mileageHistoryList  = mileageHistoryRepository.findAll();
        List<MileageHistoryDto.Res> resList = new ArrayList<>();

        for(MileageHistory mileageHistory : mileageHistoryList) {
            resList.add(new MileageHistoryDto.Res(mileageHistory));
        }

        return ResponseEntity.status(HttpStatus.OK).body(resList);
    }
}
