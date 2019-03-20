package com.example.logfileserver.dtos;

import com.example.logfileserver.controllers.dtos.UserDTO;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class UserDTOTest {

    @Test
    public void constructor() {
        UserDTO dto = new UserDTO(123, "name123");
        Assertions.assertThat(dto.getId()).isEqualTo(123);
        Assertions.assertThat(dto.getName()).isEqualTo("name123");
    }
}
