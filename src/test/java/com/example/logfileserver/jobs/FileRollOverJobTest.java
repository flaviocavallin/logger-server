package com.example.logfileserver.jobs;

import com.example.logfileserver.services.OutputStreamManager;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class FileRollOverJobTest {

    private OutputStreamManager outputStreamManager = Mockito.mock(OutputStreamManager.class);

    private FileRollOverJob job;

    @Before
    public void setUp() {
        this.job = new FileRollOverJob(outputStreamManager);
    }
    @Test
    public void rolloverTest() {
        this.job.rollover();

        Mockito.verify(outputStreamManager).activateNextOutputStream();
        Mockito.verify(outputStreamManager).getNextOutputStream();
    }
}
