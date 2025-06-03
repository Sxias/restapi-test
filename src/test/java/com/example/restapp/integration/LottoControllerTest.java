package com.example.restapp.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class LottoControllerTest {
    @Autowired
    private ObjectMapper om;

    @Autowired
    private MockMvc mvc;

    @BeforeEach
    public void setUp() {
        // 테스트 시작 전에 실행할 코드

    }

    @Test
    public void make_lotto_test() throws Exception {
        // when -> 테스트 실행
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .post("/lotto")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // eye -> 결과 눈으로 검증
        String responseBody = actions.andReturn().getResponse().getContentAsString();

        // then -> 결과를 코드로 검증 // json의 최상위 객체를 $ 표기한다
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.numbers").isNotEmpty());
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.numbers", hasSize(6)));
    }
}
