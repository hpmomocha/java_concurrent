package com.hpe.kevin;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.HelloWorld")
public class HelloWorld {
    public static void main(String[] args) {
        log.debug("Hello World!");

        new Thread(() -> log.debug("Hello World in Sub Thread!")).start();
    }
}
