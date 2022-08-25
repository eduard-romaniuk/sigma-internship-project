package software.sigma.internship.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.Map;

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

    @Autowired
    ObjectMapper objectMapper;

    @Nested
    public class FindLatest {

        @Test
        void successful() throws Exception {
            mockMvc.perform(get("/statistic-data/latest"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(2));
        }
    }

    @Nested
    public class GetDatasetByLossType {
        @Test
        void noParam() throws Exception {
            MvcResult result = mockMvc
                    .perform(get("/statistic-data/dataset"))
                    .andExpect(status().isOk())
                    .andReturn();
            Map<LocalDate, Integer> map = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            Assert.isTrue(map.size() == 3, "No 3 values in map: " + map);
        }

        @Test
        void validParam() throws Exception {
            MvcResult result = mockMvc
                    .perform(get("/statistic-data/dataset")
                            .param("lossType", "tanks"))
                    .andExpect(status().isOk())
                    .andReturn();
            Map<LocalDate, Integer> map = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            Assert.isTrue(
                    map.get(LocalDate.parse("2010-10-20")) == 2
                            & map.get(LocalDate.parse("2010-10-21")) == 22
                            & map.get(LocalDate.parse("2010-10-19")) == 12,
                    "Dates do not match with its loss tanks values");
        }

        @Test
        void invalidParam() throws Exception {
            mockMvc
                    .perform(get("/statistic-data/dataset")
                            .param("lossType", "INVALID_PARAMETER"))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    public class GetMathCalculations {

        @Test
        void noParam() throws Exception {
            MvcResult result = mockMvc
                    .perform(get("/statistic-data/math"))
                    .andExpect(status().isOk())
                    .andReturn();
            Map<String, Number> map = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            Assert.isTrue(map.size() == 4, "No 4 values in map: " + map);
        }

        @Test
        void validParam() throws Exception {
            MvcResult result = mockMvc
                    .perform(get("/statistic-data/math")
                            .param("lossType", "tanks"))
                    .andExpect(status().isOk())
                    .andReturn();
            Map<String, Number> map = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            System.out.println(map);

            Assert.isTrue(
                    map.get("max").equals(22)
                            & map.get("min").equals(2)
                            & map.get("mean").equals(12.0)
                            & map.get("median").equals(12.0), "Invalid calculations");
        }

        @Test
        void invalidParam() throws Exception {
            mockMvc
                    .perform(get("/statistic-data/math")
                            .param("lossType", "INVALID_PARAMETER"))
                    .andExpect(status().isBadRequest());
        }
    }

}
