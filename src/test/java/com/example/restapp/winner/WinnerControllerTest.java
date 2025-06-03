package com.example.restapp.winner;


import com.example.restapp.lotto.Lotto;
import com.example.restapp.lotto.LottoRepository;
import com.example.restapp.lotto.winner.Winner;
import com.example.restapp.lotto.winner.WinnerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class WinnerControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private LottoRepository lottoRepository;

    @Autowired
    private WinnerRepository winnerRepository;

    @Autowired
    private ObjectMapper om;

    @BeforeEach
    void setUp() {
        lottoRepository.save(
                Lotto.builder()
                        .number_1(1).number_2(2).number_3(3)
                        .number_4(4).number_5(5).number_6(6)
                        .build()
        );

        lottoRepository.save(
                Lotto.builder()
                        .number_1(1).number_2(2).number_3(3)
                        .number_4(10).number_5(11).number_6(12)
                        .build()
        );

        lottoRepository.save(
                Lotto.builder()
                        .number_1(20).number_2(21).number_3(22)
                        .number_4(23).number_5(24).number_6(25)
                        .build()
        );
    }

    @Test
    void check_winner_test() throws Exception {
        // when: 추첨 로직 실행
        mvc.perform(post("/test/winner/simulate"))
                .andExpect(status().isOk());

        // then: winner_tb에 모든 등수 저장됐는지 확인
        List<Winner> winners = winnerRepository.findAllWinners();
        assertThat(winners).hasSize(8);

        // 확인용 출력
        for (Winner winner : winners) {
            System.out.printf("로또 ID: %d, 등수: %d%n", winner.getLotto().getId(), winner.getRank());
        }
        // 실제 등수 검증은 무작위 로또 번호 생성 결과에 따라 달라질 수 있으므로,
        // 고정된 번호로 테스트하려면 makeLotto()를 Mock 처리하거나 테스트용 구현을 제공해야 합니다.
    }
}
