package com.example.restapp.lotto;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class LottoRepository {
    private final EntityManager em;

    // 테스트용 DB 저장 메서드
    public void save(Lotto lotto) {
        em.persist(lotto);
    }
}
