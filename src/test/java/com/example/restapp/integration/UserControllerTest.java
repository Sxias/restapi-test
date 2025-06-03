package com.example.restapp.integration;

import com.example.restapp.user.User;
import com.example.restapp.user.UserRequest;
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

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class UserControllerTest {
    @Autowired
    private ObjectMapper om;

    @Autowired
    private MockMvc mvc;

    @BeforeEach
    public void setUp() {
        // 테스트 시작 전에 실행할 코드
        System.out.println("setUp");
        User testUser = User.builder()
                .id(1L)
                .name("문정준")
                .build();
    }

    @Test
    public void join_test() throws Exception {
        // given -> 가짜 데이터
        UserRequest.DTO reqDTO = new UserRequest.DTO();
        reqDTO.setName("김영희");

        String requestBody = om.writeValueAsString(reqDTO);

        // when -> 테스트 실행
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .post("/users")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // eye -> 결과 눈으로 검증
        String responseBody = actions.andReturn().getResponse().getContentAsString();

        // then -> 결과를 코드로 검증 // json의 최상위 객체를 $ 표기한다
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("김영희"));
    }

    @Test
    public void join_fail_test() throws Exception {
        // given -> 가짜 데이터
        UserRequest.DTO reqDTO = new UserRequest.DTO();
        reqDTO.setName("김철수");

        String requestBody = om.writeValueAsString(reqDTO);

        // when -> 테스트 실행
        ResultActions actions = mvc.perform( // 주소가 틀리면 터지고, json 아닌거 넣으면 터지고, 타입이 달라도 터지고. 따라서 미리 터진다고 알려줌
                MockMvcRequestBuilders
                        .post("/users")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // eye -> 결과 눈으로 검증
        String responseBody = actions.andReturn().getResponse().getContentAsString();

        // then -> 결과를 코드로 검증 // json의 최상위 객체를 $ 표기한다
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.reason").value("이미 존재하는 회원입니다."));
    }

    @Test
    public void get_test() throws Exception {
        // given -> 가짜 데이터
        Long id = 4L;

        // when -> 테스트 실행
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .get("/users/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // eye -> 결과 눈으로 검증
        String responseBody = actions.andReturn().getResponse().getContentAsString();

        // then -> 결과를 코드로 검증 // json의 최상위 객체를 $ 표기한다
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(4));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("가나다"));
    }

    @Test
    public void get_fail_test() throws Exception {
        // given -> 가짜 데이터
        Long id = 7L;

        // when -> 테스트 실행
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .get("/users/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // eye -> 결과 눈으로 검증
        String responseBody = actions.andReturn().getResponse().getContentAsString();

        // then -> 결과를 코드로 검증 // json의 최상위 객체를 $ 표기한다
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.reason").value("해당하는 유저를 찾을 수 없습니다."));
    }

    @Test
    public void update_test() throws Exception {
        // given -> 가짜 데이터
        Long id = 1L;

        UserRequest.DTO reqDTO = new UserRequest.DTO();
        reqDTO.setName("테스트");

        String requestBody = om.writeValueAsString(reqDTO);

        // when -> 테스트 실행
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .put("/users/{id}", id)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // eye -> 결과 눈으로 검증
        String responseBody = actions.andReturn().getResponse().getContentAsString();

        // then -> 결과를 코드로 검증 // json의 최상위 객체를 $ 표기한다
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("테스트"));
    }

    @Test
    public void update_fail_by_wrong_id_test() throws Exception {
        // given -> 가짜 데이터
        Long id = 7L;

        UserRequest.DTO reqDTO = new UserRequest.DTO();
        reqDTO.setName("테스트");

        String requestBody = om.writeValueAsString(reqDTO);

        // when -> 테스트 실행
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .put("/users/{id}", id)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // eye -> 결과 눈으로 검증
        String responseBody = actions.andReturn().getResponse().getContentAsString();

        // then -> 결과를 코드로 검증 // json의 최상위 객체를 $ 표기한다
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.reason").value("해당하는 유저를 찾을 수 없습니다."));
    }

    @Test
    public void update_fail_by_existing_name_test() throws Exception {
        // given -> 가짜 데이터
        Long id = 1L;

        UserRequest.DTO reqDTO = new UserRequest.DTO();
        reqDTO.setName("홍길동");

        String requestBody = om.writeValueAsString(reqDTO);

        // when -> 테스트 실행
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .put("/users/{id}", id)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // eye -> 결과 눈으로 검증
        String responseBody = actions.andReturn().getResponse().getContentAsString();

        // then -> 결과를 코드로 검증 // json의 최상위 객체를 $ 표기한다
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.reason").value("이미 해당 이름을 가진 유저가 존재합니다."));
    }

    @Test
    public void uri_validation_test() throws Exception {
        // given -> 가짜 데이터
        Long id = 1L;

        // when -> 테스트 실행
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .get("/users/{id}?name=test!!", id)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // eye -> 결과 눈으로 검증
        String responseBody = actions.andReturn().getResponse().getContentAsString();

        // then -> 결과를 코드로 검증 // json의 최상위 객체를 $ 표기한다
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.reason").value("요청 주소에 허용되지 않은 문자가 포함되어 있습니다."));
    }
}
