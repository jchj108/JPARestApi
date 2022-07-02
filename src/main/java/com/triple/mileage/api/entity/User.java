package com.triple.mileage.api.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
public class User {

    @Id
    @Column(name = "userId")
    @Type(type = "uuid-char")
    private UUID id;

    @OneToOne(mappedBy = "user", fetch = LAZY)
    private Mileage mileage;

    @OneToMany(mappedBy = "user")
    private List<MileageHistory> mileageHistories = new ArrayList<>();

}
