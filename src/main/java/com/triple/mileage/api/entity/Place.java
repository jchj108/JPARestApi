package com.triple.mileage.api.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class Place {

    @Id
    @Type(type = "uuid-char")
    @Column(name = "placeId")
    private UUID id;

    private long reviewCount;

    @OneToOne(mappedBy = "place", fetch = LAZY)
    private Review review;
}
