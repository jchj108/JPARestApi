package com.triple.mileage.api.domain;

import com.triple.mileage.api.dto.ReviewDto;
import lombok.Builder;
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

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "placeId")
    private Place place;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<ReviewPhoto> attachedPhotoList = new ArrayList<>();

    public void addReviewPhoto(ReviewPhoto reviewPhoto) {
        this.attachedPhotoList.add(reviewPhoto);
        reviewPhoto.setReview(this);
    }

    public void setPlace(Place place) {
        this.place = place;
        place.setReview(this);
    }

    public void setUser(User user) {
        this.user = user;
        user.getReviewList().add(this);
    }

    public static Review createReview(User user, Place place, List<ReviewPhoto> reviewPhotos, ReviewDto.ReviewRequest dto) {
        Review review = new Review();
        review.setId(dto.getReviewId());
        review.setUser(user);
        review.setPlace(place);
        review.setContent(dto.getContent());

        for (ReviewPhoto reviewPhoto : reviewPhotos) {
            review.addReviewPhoto(reviewPhoto);
        }
        return review;
    }

    public void updatePoint(Long point) {
        try {
            this.user.setPoint(this.user.getPoint() + point);
        } catch (NullPointerException exception) {
            this.user.setPoint(point);
        }
    }

    public void updatePlaceReviewCount(Place place) {
        try {
            this.place.setReviewCount(place.getReviewCount() + 1);
        } catch (NullPointerException exception) {
            this.place.setReviewCount(1);
        }
    }
}
