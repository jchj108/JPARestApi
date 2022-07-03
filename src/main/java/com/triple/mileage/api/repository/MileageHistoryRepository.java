package com.triple.mileage.api.repository;


import com.triple.mileage.api.domain.MileageHistory;
import com.triple.mileage.api.domain.Place;
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
}
