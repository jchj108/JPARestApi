package com.triple.mileage.api.repository;

import com.triple.mileage.api.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public UUID save(User user) {
        em.persist(user);
        return user.getId();
    }
    public User findOne(UUID id) {
        return em.find(User.class, id);
    }

    public List<User> findAll() {
        return em.createQuery("select A from User A", User.class)
                .getResultList();
    }
}
