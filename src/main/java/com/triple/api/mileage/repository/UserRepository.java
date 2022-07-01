package com.triple.api.mileage.repository;

import com.triple.api.mileage.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public UUID save(User user) {
        em.persist(user);
        return user.getId();
    }

    public User findById(UUID id) {
        return em.find(User.class, id);
    }
}
