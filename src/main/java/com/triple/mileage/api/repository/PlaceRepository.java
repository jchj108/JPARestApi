package com.triple.mileage.api.repository;

import com.triple.mileage.api.domain.Place;
import com.triple.mileage.api.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PlaceRepository {

    private final EntityManager em;

    public UUID save(Place place) {
        em.persist(place);
        return place.getId();
    }
    public Place findOne(UUID id) {
        return em.find(Place.class, id);
    }

    public List<Place> findAll() {
        return em.createQuery("select A from Place A", Place.class)
                .getResultList();
    }
}
