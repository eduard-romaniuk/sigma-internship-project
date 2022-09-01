package software.sigma.internship.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import software.sigma.internship.dto.FundDto;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "/scripts/create-fund.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/scripts/clear-fund.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class FundControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Nested
    public class FindAll {
        @Test
        @WithMockUser(username = "user", password = "user", authorities = "USER")
        void successful() throws Exception {
            mockMvc.perform(get("/fund"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.size()", greaterThan(0)));
        }
    }

    @Nested
    public class FindById {
        @Test
        @WithMockUser(username = "user", password = "user", authorities = "USER")
        void successful() throws Exception {
            mockMvc.perform(get("/fund/1"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(1));
        }

        @Test
        @WithMockUser(username = "user", password = "user", authorities = "USER")
        void notFound() throws Exception {
            mockMvc.perform(get("/fund/3"))
                    .andDo(print())
                    .andExpect(status().isNotFound());
        }
    }

    @Nested
    public class AddFund {
        FundDto testEntity = new FundDto(3, "test fund", "test fund desc", "test_fund_site.com");
        FundDto testInvalidEntity = new FundDto(3, "", "", "test fund link");
        FundDto testInvalidURLEntity = new FundDto(3, "test fund", "test fund desc", "testfundlink");

        @Test
        @WithMockUser(username = "admin", password = "admin", authorities = "ADMIN")
        void successful() throws Exception {
            mockMvc.perform(post("/fund")
                    .content(objectMapper.writeValueAsString(testEntity))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.name").value(testEntity.name()))
                    .andExpect(jsonPath("$.id", notNullValue()));
        }

        @Test
        @WithMockUser(username = "admin", password = "admin", authorities = "ADMIN")
        void blankField() throws Exception {
            mockMvc.perform(post("/fund")
                    .content(objectMapper.writeValueAsString(testInvalidEntity))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isBadRequest());
        }

        @Test
        @WithMockUser(username = "admin", password = "admin", authorities = "ADMIN")
        void invalidURL() throws Exception {
            mockMvc.perform(post("/fund")
                    .content(objectMapper.writeValueAsString(testInvalidURLEntity))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isBadRequest());
        }

        @Test
        @WithMockUser(username = "user", password = "user", authorities = "USER")
        void forbidden() throws Exception {
            mockMvc.perform(post("/fund")
                            .content(objectMapper.writeValueAsString(testEntity))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isForbidden());
        }
    }

    @Nested
    public class DeleteFundById {

        @Test
        @WithMockUser(username = "admin", password = "admin", authorities = "ADMIN")
        void successful() throws Exception {
            mockMvc.perform(delete("/fund/1"))
                    .andDo(print())
                    .andExpect(status().isNoContent());
        }

        @Test
        @WithMockUser(username = "user", password = "user", authorities = "USER")
        void forbidden() throws Exception {
            mockMvc.perform(delete("/fund/1"))
                    .andDo(print())
                    .andExpect(status().isForbidden());
        }
    }

    @Nested
    public class UpdateFundById {
        FundDto testEntity = new FundDto(0, "updated test fund",
                "updated test fund desc", "http://updated_test_fund_link.com");
        FundDto testBlankEntity = new FundDto(0, "", "", "updated test fund link");
        FundDto testInvalidURLEntity = new FundDto(0, "updated test fund",
                "updated test fund desc", "http://updated_test_fund_link");

        @Test
        @WithMockUser(username = "admin", password = "admin", authorities = "ADMIN")
        void successful() throws Exception {
            mockMvc.perform(put("/fund/1")
                    .content(objectMapper.writeValueAsString(testEntity))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isAccepted())
                    .andExpect(jsonPath("$.name").value(testEntity.name()))
                    .andExpect(jsonPath("$.description").value(testEntity.description()))
                    .andExpect(jsonPath("$.link").value(testEntity.link()));
        }

        @Test
        @WithMockUser(username = "admin", password = "admin", authorities = "ADMIN")
        void blankField() throws Exception {
            mockMvc.perform(put("/fund/1")
                    .content(objectMapper.writeValueAsString(testBlankEntity))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isBadRequest());
        }

        @Test
        @WithMockUser(username = "admin", password = "admin", authorities = "ADMIN")
        void invalidURL() throws Exception {
            mockMvc.perform(put("/fund/1")
                    .content(objectMapper.writeValueAsString(testInvalidURLEntity))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isBadRequest());
        }

        @Test
        @WithMockUser(username = "user", password = "user", authorities = "USER")
        void forbidden() throws Exception {
            mockMvc.perform(put("/fund/1")
                            .content(objectMapper.writeValueAsString(testEntity))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isForbidden());
        }
    }
}
