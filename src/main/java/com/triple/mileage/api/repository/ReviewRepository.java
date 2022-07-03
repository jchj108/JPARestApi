package com.triple.mileage.api.repository;


import com.triple.mileage.api.domain.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ReviewRepository {

    private final EntityManager em;

    public void save(Review review) {
        em.persist(review);
    }

    public Review findOne(UUID id) {
        return em.find(Review.class, id);
    }

    public List<Review> findAll() {
        return em.createQuery("select A from Review A", Review.class)
                .getResultList();
    }

    public Review findOneByUserId(UUID userId, UUID placeId) {
        try {
            return em.createQuery("select A from Review A where A.user.id = :userId and A.place.id = :placeId", Review.class)
                    .setParameter("userId", userId).setParameter("placeId", placeId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public int deleteReview(Review review) {
        Query query = em.createNativeQuery("delete from Review where review_id = " + "'" + review.getId() + "'");
        return query.executeUpdate();
    }
}
