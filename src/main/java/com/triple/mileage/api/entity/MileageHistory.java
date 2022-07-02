package com.triple.mileage.api.entity;

import com.triple.mileage.api.domain.MileageType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
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
}
