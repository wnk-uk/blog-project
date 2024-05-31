package com.blog.main;

import com.blog.WithAccount;
import com.blog.account.AccountRepository;
import com.blog.oauth2.CustomOAuth2UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class MainControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    CustomOAuth2UserService customOAuth2UserService;
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ClientRegistrationRepository clientRegistrationRepository;

    @Value("${oauth2.redirect_url}")
    String redirectURL;

    private final String OAUTH2_URL = "http://localhost:8080/oauth2/authorization/google";
    private final String REDIRECT_URL = "http://localhost:8080/login/oauth2/code/google";

    @DisplayName("OAuth user 조회 테스트 - 존재하는 계정")
    @Test
    public void google_oauth2_login_page() throws Exception {
        mockMvc.perform(
                get(OAUTH2_URL)
                        //.with(request -> request.)
        ).andExpect(status().isFound()) //302
        .andExpect(MockMvcResultMatchers.header().exists("Location")) //Location in header
        .andExpect(MockMvcResultMatchers.header().string("Location", is(startsWith("https://accounts.google.com/o/oauth2/v2/auth"))))
        .andDo(print());
    }

    //TODO
    //어세스토큰 관련
    //인증토큰 관련


    @WithAccount
    @DisplayName("메인 페이지 접속") // 권한을 풀어놓았기 때문에 추후 api로 제공될 서비스에 재 테스트
    @Test
    public void main_page_test() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(authenticated().withUsername("Uk NamGung"));
    }




}
