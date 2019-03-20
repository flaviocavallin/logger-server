package com.example.logfileserver.controllers;

import com.example.logfileserver.controllers.dtos.UserDTO;
import com.example.logfileserver.services.WriterService;
import com.example.protobuf.UserProtos;
import com.google.protobuf.MessageLite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping(value = "/log", consumes = MediaType.APPLICATION_JSON_VALUE)
class LogController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogController.class);

    private final WriterService<MessageLite> fileWriterService;

    LogController(WriterService<MessageLite> fileWriterService) {
        this.fileWriterService = Objects.requireNonNull(fileWriterService, "fileWriterService can not be null");
    }


    @PostMapping()
    public void log(@RequestBody UserDTO userDTO) {
        LOGGER.debug("userDTO={}", userDTO);

        UserProtos.User user = UserProtos.User.newBuilder().setId(userDTO.getId()).setName(userDTO.getName()).build();

        this.fileWriterService.write(user);
    }
}