package com.triple.mileage.api.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @Column(name = "userId")
    @Type(type = "uuid-char")
    private UUID id;

    @ColumnDefault("0")
    private long point;

    @OneToMany(mappedBy = "user", fetch = LAZY)
    private List<MileageHistory> mileageHistories = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = LAZY)
    private List<Review> reviewList = new ArrayList<>();

    @Builder
    public User(UUID id, long point, List<MileageHistory> mileageHistories, List<Review> reviewList) {
        this.id = id;
        this.point = point;
        this.mileageHistories = mileageHistories;
        this.reviewList = reviewList;
    }
}
