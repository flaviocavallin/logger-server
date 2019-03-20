package com.example.logfileserver.controllers.dtos;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;

public class UserDTO {
    private int id;
    private String name;

    public UserDTO(int id, String name) {
        this.id = id;
        this.name = Objects.requireNonNull(name, "name can not be null");
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
