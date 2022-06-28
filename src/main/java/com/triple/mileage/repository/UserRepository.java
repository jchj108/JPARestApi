package com.triple.mileage.repository;

import com.triple.mileage.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public UUID save(User user) {
        em.persist(user);
        return user.getId();
    }

    public User find(UUID id) {
        return em.find(User.class, id);
    }
}
