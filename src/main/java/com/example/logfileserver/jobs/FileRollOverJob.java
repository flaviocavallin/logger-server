package com.example.logfileserver.jobs;

import com.example.logfileserver.services.OutputStreamManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
class FileRollOverJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileRollOverJob.class);


    private final OutputStreamManager outputStreamManager;

    FileRollOverJob(OutputStreamManager outputStreamManager) {
        this.outputStreamManager= Objects.requireNonNull(outputStreamManager, "outputStreamManager can not be null");
    }

    @Scheduled(fixedRate = 60000, initialDelay = 10)
    public void rollover() {
        LOGGER.info("Executing the fileRollOver job...");

        //=Create a new outputStream
        this.outputStreamManager.getNextOutputStream();

        //= Remove the previous outputStream and active the new one
        this.outputStreamManager.activateNextOutputStream();
    }
}
