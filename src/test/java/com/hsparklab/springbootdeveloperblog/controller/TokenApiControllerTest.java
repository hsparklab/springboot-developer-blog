package com.hsparklab.springbootdeveloperblog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hsparklab.springbootdeveloperblog.config.jwt.JwtFactory;
import com.hsparklab.springbootdeveloperblog.config.jwt.JwtProperties;
import com.hsparklab.springbootdeveloperblog.domain.Article;
import com.hsparklab.springbootdeveloperblog.domain.RefreshToken;
import com.hsparklab.springbootdeveloperblog.domain.User;
import com.hsparklab.springbootdeveloperblog.dto.AddArticleRequest;
import com.hsparklab.springbootdeveloperblog.dto.CreateAccessTokenRequest;
import com.hsparklab.springbootdeveloperblog.dto.UpdateArticleRequest;
import com.hsparklab.springbootdeveloperblog.repository.BlogRepository;
import com.hsparklab.springbootdeveloperblog.repository.RefreshTokenRepository;
import com.hsparklab.springbootdeveloperblog.repository.UserRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
class TokenApiControllerTest {
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    JwtProperties jwtProperties;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    @BeforeEach // 테스트 실행 전
    public void mockMvcSetUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
        userRepository.deleteAll();
    }

    @DisplayName("createNewAccessToken : 새로운 엑세스 토큰 발급")
    @Test
    public void createdNewAccessToken() throws Exception{
        //given
        final String url = "/api/token";
        User testUser = userRepository.save(User.builder()
                .email("user@gmail.com")
                .password("test")
                .build());
        String refreshToken = JwtFactory.builder()
                .claims(Map.of("id", testUser.getId()))
                .build()
                .createToken(jwtProperties);
        refreshTokenRepository.save(new RefreshToken(testUser.getId(), refreshToken));

        CreateAccessTokenRequest request = new CreateAccessTokenRequest();
        request.setRefreshToken(refreshToken);
        final String requestBody = objectMapper.writeValueAsString(request);
        //when
        ResultActions resultActions = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));
        //then
        resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.accessToken").isNotEmpty());
    }


}