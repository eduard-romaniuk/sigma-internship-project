package software.sigma.internship.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Nested;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import software.sigma.internship.dto.AuthUserDto;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "/scripts/create-users.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/scripts/clear-user.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Nested
    public class FindAll {

        @Test
        @WithAnonymousUser
        void anonymousFail() throws Exception {
            mockMvc.perform(get("/user"))
                    .andDo(print())
                    .andExpect(status().isUnauthorized());
        }

        @Test
        @WithMockUser(authorities = "USER")
        void userFail() throws Exception {
            mockMvc.perform(get("/user"))
                    .andDo(print())
                    .andExpect(status().isForbidden());
        }

        @Test
        @WithMockUser(authorities = "ADMIN")
        void adminSuccess() throws Exception {
            mockMvc.perform(get("/user"))
                    .andDo(print())
                    .andExpect(status().isOk());
        }
    }

    @Nested
    public class Registration {
        @Test
        void Should_ReturnBadRequest_When_AuthUserDtoHasAllEmptyFields() throws Exception {
            AuthUserDto user = new AuthUserDto(0, "", "", "");

            mockMvc.perform(post("/user")
                            .content(objectMapper.writeValueAsString(user))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isBadRequest());

        }

        @Test
        void Should_ReturnBadRequest_When_AuthUserDtoHasTwoEmptyField() throws Exception {
            AuthUserDto user = new AuthUserDto(0, "Joe", "", "");

            mockMvc.perform(post("/user")
                            .content(objectMapper.writeValueAsString(user))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isBadRequest());

        }

        @Test
        void Should_ReturnBadRequest_When_AuthUserDtoHasOneEmptyField() throws Exception {
            AuthUserDto user = new AuthUserDto(0, "Joe", "joe@email.com", "");

            mockMvc.perform(post("/user")
                            .content(objectMapper.writeValueAsString(user))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isBadRequest());

        }

        @Test
        void Should_ReturnBadRequest_When_AuthUserDtoHasInvalidEmail() throws Exception {
            AuthUserDto user = new AuthUserDto(0, "Joe", "joe.email.com", "joe");

            mockMvc.perform(post("/user")
                            .content(objectMapper.writeValueAsString(user))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isBadRequest());

        }

        @Test
        void Should_ReturnUserDto_When_AuthUserDtoIsValid() throws Exception {
            AuthUserDto user = new AuthUserDto(0, "Joe", "joe@email.com", "joe");

            mockMvc.perform(post("/user")
                            .content(objectMapper.writeValueAsString(user))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.name").value(user.name()))
                    .andExpect(jsonPath("$.email").value(user.email()))
                    .andExpect(jsonPath("$.id", notNullValue()));

        }
    }

    @Nested
    public class AuthenticatedUser {
        String userEmail = "userD@email.com";
        String adminEmail = "adminU@email.com";
        String userPassword = "user";
        String adminPassword = "admin";
        String userRole = "USER";
        String adminRole = "ADMIN";
        @Test
        void Should_ReturnUserFullDto_When_UserIsValid() throws Exception {
            mockMvc.perform(get("/user/login")
                    .with(httpBasic(userEmail,userPassword)))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.email").value(userEmail))
                    .andExpect(jsonPath("$.role").value(userRole))
                    .andExpect(jsonPath("$.id", notNullValue()));

        }

        @Test
        void Should_ReturnUserFullDto_When_AdminIsValid() throws Exception {
            mockMvc.perform(get("/user/login")
                            .with(httpBasic(adminEmail,adminPassword)))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.email").value(adminEmail))
                    .andExpect(jsonPath("$.role").value(adminRole))
                    .andExpect(jsonPath("$.id", notNullValue()));

        }
    }
}
