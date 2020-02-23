package com.milkywire.impacterservice.rest;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.milkywire.impacterservice.dto.ImpacterDto;
import java.io.IOException;
import javax.inject.Inject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class ImpacterControllerIntegrationTest {

    @Inject
    private MockMvc mvc;

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void getHello() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/impacters").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        ImpacterDto dto = convertToObject(result.getResponse().getContentAsByteArray(), ImpacterDto.class);

        assertEquals("Testing return value from controller", "Hello Samir", dto.getMessage());
    }

    private static <T> T convertToObject(byte[] contentAsByteArray, Class<T> clazz) throws IOException {
        JavaType type = objectMapper.getTypeFactory().constructType(clazz);
        return objectMapper.readValue(contentAsByteArray, type);
    }
}
