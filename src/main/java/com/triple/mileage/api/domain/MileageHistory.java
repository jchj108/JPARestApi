package com.triple.mileage.api.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MileageHistory {

    @Id @GeneratedValue
    @Column(name = "mileageHistoryId")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="userId")
    private User user;

    private Long point;

    @Enumerated(EnumType.STRING)
    private MileageType type;

    @ManyToOne(fetch = LAZY)
    @Nullable
    @JoinColumn(name="reviewId")
    private Review review;

    private LocalDateTime createdDate;

    public static MileageHistory createMileageHistory(User user, Long point, MileageType type, Review review) {
        MileageHistory mileageHistory = new MileageHistory();
        mileageHistory.setUser(user);
        mileageHistory.setReview(review);
        mileageHistory.setPoint(point);
        mileageHistory.setCreatedDate(LocalDateTime.now());
        mileageHistory.setType(type);

        return mileageHistory;
    }
}
