package com.example.restapp.lotto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

@RequiredArgsConstructor
@Service
public class LottoService {
    private final LottoRepository lottoRepository;

    public LottoResponse.DTO makeLotto() {
        // TreeSet : 내부 항목들을 오름차순으로 자동 정렬
        Set<Integer> lottoSet = new TreeSet<>();

        // 로또 번호 6개를 뽑을 때까지 반복
        while (lottoSet.size() < 6) {
            Random r = new Random();
            if (!lottoSet.contains(r.nextInt(45) + 1)) lottoSet.add(r.nextInt(45) + 1);
        }

        return new LottoResponse.DTO(lottoSet);
    }
}
