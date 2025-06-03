package com.example.restapp.user;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserRepository {
    private final EntityManager em;


    public User save(User user) {
        em.persist(user);
        return user;
    }

    public User findUserById(long userId) {
        return em.find(User.class, userId);
    }

    public User findUserByName(String name) {
        return em.createQuery("select u from User u where u.name = :name", User.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}
