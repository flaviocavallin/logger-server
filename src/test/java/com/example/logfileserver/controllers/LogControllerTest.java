package com.example.logfileserver.controllers;

import com.example.logfileserver.controllers.dtos.UserDTO;
import com.example.logfileserver.services.WriterService;
import com.example.logfileserver.utils.ControllerIntegrationBaseTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.MessageLite;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LogControllerTest extends ControllerIntegrationBaseTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private WriterService<MessageLite> fileWriterService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void logTest() throws Exception {
        doNothing().when(fileWriterService).write(ArgumentMatchers.any(MessageLite.class));

        UserDTO dto = new UserDTO(123, "name123");

        String jsonBody = objectMapper.writeValueAsString(dto);

        mvc.perform(post("/log").content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }
}
