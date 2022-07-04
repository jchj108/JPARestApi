package com.triple.mileage.api.domain;

import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Place {

    @Id
    @Type(type = "uuid-char")
    @Column(name = "placeId")
    private UUID id;

    private long reviewCount;

    @OneToOne(mappedBy = "place", fetch = LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    private Review review;

    @Builder
    public Place(UUID id, long reviewCount, Review review) {
        this.id = id;
        this.reviewCount = reviewCount;
        this.review = review;
    }
}
