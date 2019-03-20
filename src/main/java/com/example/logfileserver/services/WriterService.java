package com.example.logfileserver.services;

public interface WriterService<T> {
    void write(T message);
}
