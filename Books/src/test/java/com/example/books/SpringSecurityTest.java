package com.example.books;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@DisplayName(value = "Тесты SpringSecurity")
class SpringSecurityTest {

    @Autowired private WebApplicationContext context;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }


    //Тесты с анонимными пользователями

    @Test
    @DisplayName(value = "Анонимный пользователь на главную (OK)")
    @WithAnonymousUser
    void AnonymousUser_to_Home_isOK() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk());
    }

    @Test
    @DisplayName(value = "Анонимный пользователь на страницу входа (OK)")
    @WithAnonymousUser
    void AnonymousUser_to_Login_isOK() throws Exception {
        mockMvc.perform(get("/Login")).andExpect(status().isOk());
    }

    @Test
    @DisplayName(value = "Анонимный пользователь на страницу регистрации (OK)")
    @WithAnonymousUser
    void AnonymousUser_to_Registration_isOK() throws Exception {
        mockMvc.perform(get("/Registration")).andExpect(status().isOk());
    }

    @Test
    @DisplayName(value = "Анонимный пользователь на страницу авторов (Redirection)")
    @WithAnonymousUser
    void AnonymousUser_to_Author_isRedirection() throws Exception {
        mockMvc.perform(get("/Author")).andExpect(status().is3xxRedirection());
    }

    @Test
    @DisplayName(value = "Анонимный пользователь на страницу Издательств (Redirection)")
    @WithAnonymousUser
    void AnonymousUser_to_Publish_isRedirection() throws Exception {
        mockMvc.perform(get("/Publish")).andExpect(status().is3xxRedirection());
    }

    @Test
    @DisplayName(value = "Анонимный пользователь на страницу Книг (Redirection)")
    @WithAnonymousUser
    void AnonymousUser_to_Book_isRedirection() throws Exception {
        mockMvc.perform(get("/Book")).andExpect(status().is3xxRedirection());
    }



    //Тесты с авторизованными пользователями
    @Test
    @DisplayName(value = "Авторизованный пользователь на главную (OK)")
    @WithMockUser
    void MockUser_to_Home_isOK() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk());
    }

    @Test
    @DisplayName(value = "Авторизованный пользователь на страницу входа (Forbidden)")
    @WithMockUser
    void MockUser_to_Login_isOk() throws Exception {
        mockMvc.perform(get("/Login")).andExpect(status().isForbidden());
    }

    @Test
    @DisplayName(value = "Авторизованный пользователь на страницу регистрации (Forbidden)")
    @WithMockUser
    void MockUser_to_Registration_isOk() throws Exception {
        mockMvc.perform(get("/Registration")).andExpect(status().isForbidden());
    }

    @Test
    @DisplayName(value = "Авторизованный пользователь на страницу авторов (OK)")
    @WithMockUser
    void MockUser_to_Author_isOk() throws Exception {
        mockMvc.perform(get("/Author")).andExpect(status().isOk());
    }

    @Test
    @DisplayName(value = "Авторизованный пользователь на страницу издательств (OK)")
    @WithMockUser
    void MockUser_to_Publish_isOk() throws Exception {
        mockMvc.perform(get("/Publish")).andExpect(status().isOk());
    }

    @Test
    @DisplayName(value = "Авторизованный пользователь на страницу книг (OK)")
    @WithMockUser
    void MockUser_to_Book_isOk() throws Exception {
        mockMvc.perform(get("/Book")).andExpect(status().isOk());
    }
}