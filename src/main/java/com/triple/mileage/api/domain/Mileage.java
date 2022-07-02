package com.triple.mileage.api.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class Mileage {

    @Id @GeneratedValue
    @Column(name = "mileageId")
    private Long id;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private User user;

    private Long point;
}
