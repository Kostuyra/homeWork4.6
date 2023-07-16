package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.stream.LongStream;


@Service
public class InfoService {
    @Value("${server.port}")
    private String port;
    public String getPort(){
        return port;
    }

    public Long getLong(){
        return LongStream.range(1, 1_000_000).sum();
    }



}
