package com.example.restapp.lotto.winner;

import com.example.restapp.lotto.Lotto;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class WinnerRepository {
    private final EntityManager em;

    public List<Winner> findAllWinners() {
        return em.createQuery("select w from Winner w", Winner.class).getResultList();
    }

    public List<Lotto> findAllLottos() {
        return em.createQuery("select l from Lotto l", Lotto.class).getResultList();
    }

    public void save(Winner winner) {
        em.persist(winner);
    }
}
