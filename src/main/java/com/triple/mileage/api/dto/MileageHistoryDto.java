package com.triple.mileage.api.dto;

import com.triple.mileage.api.domain.MileageHistory;
import com.triple.mileage.api.domain.MileageType;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

public class MileageHistoryDto {


    @Getter
    public static class Res {

        private Long mileageHistoryId;
        private UUID userId;
        private Long pointHistory;
        private long point;
        private MileageType type;
        private UUID reviewId;
        private LocalDateTime createdDate;


        public Res(MileageHistory mileageHistory) {
            this.mileageHistoryId = mileageHistory.getId();
            this.userId = mileageHistory.getUser().getId();
            this.pointHistory = mileageHistory.getPointHistory();
            this.point = mileageHistory.getPoint();
            this.type = mileageHistory.getType();
            this.reviewId = mileageHistory.getReview().getId();
            this.createdDate = mileageHistory.getCreatedDate();
        }
    }
}
