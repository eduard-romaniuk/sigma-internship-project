package software.sigma.internship.controller;

import org.junit.jupiter.api.Nested;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

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
        @WithMockUser(username = "user", password = "user", authorities = "USER")
        void userFail() throws Exception {
            mockMvc.perform(get("/user"))
                    .andDo(print())
                    .andExpect(status().isForbidden());
        }

        @Test
        @WithMockUser(username = "admin", password = "admin", authorities = "ADMIN")
        void adminSuccess() throws Exception {
            mockMvc.perform(get("/user"))
                    .andDo(print())
                    .andExpect(status().isOk());
        }
    }
}
