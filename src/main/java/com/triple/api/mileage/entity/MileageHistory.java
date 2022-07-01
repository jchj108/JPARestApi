package com.triple.api.mileage.entity;

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

    private String type;

    private LocalDateTime createdDate;
}
