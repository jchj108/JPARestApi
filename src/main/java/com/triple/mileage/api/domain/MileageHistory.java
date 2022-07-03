package com.triple.mileage.api.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private LocalDateTime createdDate;

    public static MileageHistory createMileageHistory(User user, MileageType type) {
        MileageHistory mileageHistory = new MileageHistory();
        mileageHistory.setUser(user);
        mileageHistory.setPoint(user.getPoint());
        mileageHistory.setCreatedDate(LocalDateTime.now());
        mileageHistory.setType(type);

        return mileageHistory;
    }
}
