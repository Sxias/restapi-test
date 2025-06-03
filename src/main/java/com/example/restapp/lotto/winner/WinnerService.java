package com.example.restapp.lotto.winner;

import com.example.restapp.lotto.Lotto;
import com.example.restapp.lotto.LottoResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class WinnerService {
    private final WinnerRepository winnerRepository;

    @Transactional
    public void checkWinner(LottoResponse.DTO lottoSet) {
        Set<Integer> latestLotto = lottoSet.getNumbers();
        List<Lotto> candidates = winnerRepository.findAllLottos();

        for (Lotto candidate : candidates) {
            int matched = countMatches(latestLotto, candidate.toSet());
            int rank = calculateRank(matched);

            Winner winner = new Winner(candidate, rank);
            winnerRepository.save(winner);
        }
    }

    private int calculateRank(int maxMatch) {
        return switch (maxMatch) {
            case 6 -> 1;
            case 5 -> 2;
            case 4 -> 3;
            case 3 -> 4;
            default -> 5;
        };
    }

    private int countMatches(Set<Integer> latestLotto, Set<Integer> candidateLotto) {
        Set<Integer> copy = new HashSet<>(latestLotto);
        copy.retainAll(candidateLotto);
        return copy.size();
    }
}
