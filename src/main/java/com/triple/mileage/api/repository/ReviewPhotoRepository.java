package com.triple.mileage.api.repository;

import com.triple.mileage.api.domain.Place;
import com.triple.mileage.api.domain.Review;
import com.triple.mileage.api.domain.ReviewPhoto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ReviewPhotoRepository {

    private final EntityManager em;

    public UUID save(ReviewPhoto reviewPhoto) {
        em.persist(reviewPhoto);
        return reviewPhoto.getId();
    }
    public ReviewPhoto findOne(UUID id) {
        return em.find(ReviewPhoto.class, id);
    }

    public List<ReviewPhoto> findAll() {
        return em.createQuery("select A from ReviewPhoto A", ReviewPhoto.class)
                .getResultList();
    }

    public void deleteByReviewId(Review review) {
        Query query = em.createNativeQuery("delete from review_photo where review_id = " + "'" + review.getId() + "'");
        query.executeUpdate();
    }
}
