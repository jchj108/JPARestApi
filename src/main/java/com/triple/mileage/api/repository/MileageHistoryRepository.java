package com.triple.mileage.api.repository;


import com.triple.mileage.api.domain.MileageHistory;
import com.triple.mileage.api.domain.Review;
import com.triple.mileage.api.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class MileageHistoryRepository {

    private final EntityManager em;

    public Long save(MileageHistory mileageHistory) {
        em.persist(mileageHistory);
        return mileageHistory.getId();
    }
    public MileageHistory findOne(UUID id) {
        return em.find(MileageHistory.class, id);
    }

    public List<MileageHistory> findAll() {
        return em.createQuery("select A from MileageHistory A", MileageHistory.class)
                .getResultList();
    }

    public MileageHistory findByReviewId(UUID id) {
        return em.createQuery("select A from MileageHistory A where A.review.id = :reviewId", MileageHistory.class)
                .setParameter("reviewId", id)
                .getSingleResult();
    }

    public Long findEarendPoint(User user, Review review) {

        List<MileageHistory> list = em.createQuery("select A from MileageHistory A where A.review.id = :reviewId and A.user.id = :userId order by A.createdDate desc ", MileageHistory.class)
                                    .setParameter("reviewId", review.getId())
                                    .setParameter("userId", user.getId())
                                    .getResultList();

        long sum = 0;
        for(MileageHistory mileageHistory : list) {
            sum += mileageHistory.getPoint();
        }
        return sum;
    }

    public List<MileageHistory> findHistoriesByUser(UUID userId) {
        return em.createQuery("select A from MileageHistory A where A.user.id = :userId order by A.createdDate desc")
                .setParameter("userId", userId)
                .getResultList();

    }
}
