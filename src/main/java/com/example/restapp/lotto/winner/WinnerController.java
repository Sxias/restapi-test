package com.example.restapp.lotto.winner;

import com.example.restapp._core.util.Resp;
import com.example.restapp.lotto.LottoResponse;
import com.example.restapp.lotto.LottoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RequiredArgsConstructor
@RestController
public class WinnerController {
    private final WinnerService winnerService;
    private final LottoService lottoService;

    // Scheduler
    @Scheduled(cron = "0 0 0 ? * SUN", zone = "Asia/Seoul")
    public void checkWinner() {
        LottoResponse.DTO latestLotto = lottoService.makeLotto();
        winnerService.checkWinner(latestLotto);
    }

    // 테스트 전용
    @PostMapping("/test/winner/simulate")
    public ResponseEntity<?> simulateWinner() {
        LottoResponse.DTO latestLotto = lottoService.makeLotto();
        winnerService.checkWinner(latestLotto);
        return Resp.ok("추첨 완료");
    }
}
