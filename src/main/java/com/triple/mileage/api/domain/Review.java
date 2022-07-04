package com.triple.mileage.api.domain;

import com.triple.mileage.api.dto.ReviewDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@Where(clause = "is_deleted != 1")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {
    @Id
    @Column(name = "reviewId")
    @Type(type = "uuid-char")
    private UUID id;
    private String content;

    @ColumnDefault("0")
    private Integer isDeleted = 0;

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

    public void updateReviewPhoto(List<ReviewPhoto> reviewPhotoList) {
        for (ReviewPhoto reviewPhoto : reviewPhotoList) {
            this.attachedPhotoList.add(reviewPhoto);
            reviewPhoto.setReview(this);
        }
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
            if (reviewPhoto.getId() != null) {
                review.addReviewPhoto(reviewPhoto);
            }
        }
        return review;
    }


    public void updatePlaceReviewCount(Place place, long val) {
        try {
            this.place.setReviewCount(place.getReviewCount() + val);
        } catch (NullPointerException exception) {
            this.place.setReviewCount(1);
        }
    }

    public Review updateReview(Review review, List<ReviewPhoto> reviewPhotoList, ReviewDto.ReviewRequest dto) {
        review.setAttachedPhotoList(reviewPhotoList);
        review.setContent(dto.getContent());

        for (ReviewPhoto reviewPhoto : reviewPhotoList) {
            reviewPhoto.setReview(review);
        }
        return review;
    }
}
