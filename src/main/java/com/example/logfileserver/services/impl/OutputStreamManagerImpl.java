package com.example.logfileserver.services.impl;

import com.example.logfileserver.services.OutputStreamManager;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Deque;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedDeque;

@Component
class OutputStreamManagerImpl implements OutputStreamManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(OutputStreamManagerImpl.class);
    private static final String TEMP_DIR = System.getProperty("java.io.tmpdir");

    private final Deque<OutputStream> streams;
    private final Random rand;

    OutputStreamManagerImpl() {
        LOGGER.info("OutputStream writing into={}", TEMP_DIR);
        rand = new Random();
        streams = new ConcurrentLinkedDeque<>();
        this.getNextOutputStream();
    }


    @Override
    public OutputStream getOutputStream() {
        OutputStream outputStream = this.streams.getFirst();
        if (outputStream == null) {
            outputStream = this.getNextOutputStream();
        }
        return outputStream;
    }

    @Override
    public OutputStream getNextOutputStream() {
        if (CollectionUtils.isEmpty(this.streams) || this.streams.getLast() == this.streams.getFirst()) {
            OutputStream outputStream = createOutputStream();
            this.streams.addLast(outputStream);
        }
        return this.streams.getLast();
    }


    Path getPathFile() {
        return Paths.get(TEMP_DIR, "file" + rand.nextInt(100000));
    }

     OutputStream createOutputStream() {
         Path pathFile = getPathFile();
        try {
            return new FileOutputStream(pathFile.toFile());
        } catch (FileNotFoundException e) {
            LOGGER.error("There was an issue with outputstream", e);
            throw new IllegalStateException("Impossible to create outputStream");
        }
    }


    @Override
    public boolean activateNextOutputStream() {
        if (CollectionUtils.isNotEmpty(this.streams) && this.streams.getLast() != this.streams.getFirst()) {
            OutputStream out = this.streams.pollFirst();

            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    LOGGER.error("Error trying to close the outputStream", e);
                }
            }
            return true;
        }

        return false;
    }
}
