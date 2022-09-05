package software.sigma.internship.controller;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "/scripts/create-statistic-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/scripts/clear-statistic-data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)

public class StatisticDataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Nested
    public class FindLatest {

        @Test
        @WithAnonymousUser
        void successful() throws Exception {
            mockMvc.perform(get("/statistic-data/latest"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.warDate").value("2010-10-21"));
        }
    }

    @Nested
    public class GetDatasetByLossType {
        @Test
        @WithMockUser(username = "user", password = "user", authorities = "USER")
        void noParam() throws Exception {
            mockMvc
                    .perform(get("/statistic-data/dataset"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.size()").value(3));
        }

        @Test
        @WithMockUser(username = "user", password = "user", authorities = "USER")
        void validParam() throws Exception {
            mockMvc
                    .perform(get("/statistic-data/dataset")
                            .param("lossType", "tanks"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.size()").value(3));
        }

        @Test
        @WithMockUser(username = "user", password = "user", authorities = "USER")
        void invalidParam() throws Exception {
            mockMvc
                    .perform(get("/statistic-data/dataset")
                            .param("lossType", "INVALID_PARAMETER"))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @WithAnonymousUser
        void notAuthed() throws Exception {
            mockMvc
                    .perform(get("/statistic-data/dataset"))
                    .andExpect(status().isUnauthorized());
        }
    }

    @Nested
    public class GetMathCalculations {

        @Test
        @WithMockUser(username = "user", password = "user", authorities = "USER")
        void noParam() throws Exception {
            mockMvc
                    .perform(get("/statistic-data/math"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.size()").value(4))
                    .andExpect(jsonPath("$.max").value(21));
        }

        @Test
        @WithMockUser(username = "user", password = "user", authorities = "USER")
        void validParam() throws Exception {
            mockMvc
                    .perform(get("/statistic-data/math")
                            .param("lossType", "tanks"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.size()").value(4))
                    .andExpect(jsonPath("$.max").value(22));
        }

        @Test
        @WithMockUser(username = "user", password = "user", authorities = "USER")
        void invalidParam() throws Exception {
            mockMvc
                    .perform(get("/statistic-data/math")
                            .param("lossType", "INVALID_PARAMETER"))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @WithAnonymousUser
        void notAuthed() throws Exception {
            mockMvc
                    .perform(get("/statistic-data/math"))
                    .andExpect(status().isUnauthorized());
        }
    }
}
