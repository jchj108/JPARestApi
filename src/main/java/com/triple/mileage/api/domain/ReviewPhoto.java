package com.triple.mileage.api.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewPhoto {

    @Id
    @Column(name = "photoId")
    @Type(type = "uuid-char")
    private UUID id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="reviewId")
    private Review review;

    public static List<ReviewPhoto> createReviewPhoto(List<UUID> attachedPhotoIds) {

        ArrayList<ReviewPhoto> list = new ArrayList<ReviewPhoto>();
        for (UUID id : attachedPhotoIds) {
            if (id != null) {
                ReviewPhoto reviewPhoto = new ReviewPhoto();
                reviewPhoto.setId(id);
                list.add(reviewPhoto);
            }
        }
        return list;
    }

    public void setUser(User user) {
        this.review = review;
        review.getAttachedPhotoList().add(this);
    }
}
