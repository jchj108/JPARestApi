package com.triple.mileage.api.controller;

import com.triple.mileage.api.domain.MileageHistory;
import com.triple.mileage.api.dto.MileageHistoryDto;
import com.triple.mileage.api.repository.MileageHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    private final MileageHistoryRepository mileageHistoryRepository;

    @GetMapping(path = "mileage-histories/{userId}")
    public ResponseEntity findHistoriesByUser(@PathVariable("userId") UUID userId) {

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
