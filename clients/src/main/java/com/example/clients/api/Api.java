package com.example.clients.api;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("api")
public class Api {
    private String storage;
    private String cart;
    private String order;

}
