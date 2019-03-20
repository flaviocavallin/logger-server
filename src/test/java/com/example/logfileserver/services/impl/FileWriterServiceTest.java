package com.example.logfileserver.services.impl;

import com.example.logfileserver.services.OutputStreamManager;
import com.example.logfileserver.services.WriterService;
import com.example.protobuf.UserProtos;
import com.google.protobuf.MessageLite;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class FileWriterServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileWriterServiceTest.class);
    private static final String TEMP_DIR = System.getProperty("java.io.tmpdir");
    private final Random rand = new Random();

    final Path path = Paths.get(TEMP_DIR, "file" + rand.nextInt(100000));

    private OutputStreamManager outputStreamManage = new OutputStreamManagerImpl() {
        @Override
        protected Path getPathFile() {
            return path;
        }
    };
    private WriterService<MessageLite> writerService = new FileWriterService(outputStreamManage);

    @Test
    public void serializeAndDeserializeTest() throws IOException {
        UserProtos.User user1 = UserProtos.User.newBuilder().setName("name1").setId(1).build();
        writerService.write(user1);

        UserProtos.User user2 = UserProtos.User.newBuilder().setName("name2").setId(2).build();
        writerService.write(user2);


        List<UserProtos.User> users = new LinkedList<>();

        try (InputStream input = new FileInputStream(path.toFile())) {
            while (true) {
                UserProtos.User u = UserProtos.User.parseDelimitedFrom(input);
                if (u == null) {
                    break;
                }

                users.add(u);
                LOGGER.debug("user={}", u);
            }
        }


        Assertions.assertThat(users).isNotEmpty();
        Assertions.assertThat(users).containsExactlyInAnyOrder(user1, user2);
    }
}