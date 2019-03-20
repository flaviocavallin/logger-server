package com.example.logfileserver.services.impl;

import com.example.logfileserver.services.OutputStreamManager;
import com.example.logfileserver.services.WriterService;
import com.google.protobuf.MessageLite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
class FileWriterService implements WriterService<MessageLite> {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileWriterService.class);
    private final OutputStreamManager outputStreamManager;

    FileWriterService(OutputStreamManager outputStreamManager) {
       this.outputStreamManager= Objects.requireNonNull(outputStreamManager, "outputStreamManager can not be null");
    }


    @Override
    @Async("singleThreadPoolTaskExecutor")
    public void write(MessageLite message) {
        try {
            message.writeDelimitedTo(outputStreamManager.getOutputStream());
        } catch (IOException e) {
            LOGGER.error("Message discarded", message);
        }
    }
}
