package com.triple.mileage.api.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter

public class ReviewPhoto {

    @Id
    @Column(name = "photoId")
    @Type(type = "uuid-char")
    private UUID id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="reviewId")
    private Review review;

    public void setUser(User user) {
        this.review = review;
        review.getAttachedPhotoList().add(this);
    }
}
