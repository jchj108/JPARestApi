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
public class Review {
    @Id
    @Column(name = "reviewId")
    @Type(type = "uuid-char")
    private UUID id;
    private String content;

    @OneToMany(mappedBy = "review")
    private List<ReviewPhoto> AttachedPhotoList = new ArrayList<>();

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "placeId")
    private Place place;

}
