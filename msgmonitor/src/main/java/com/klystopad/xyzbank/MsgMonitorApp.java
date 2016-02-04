package com.klystopad.xyzbank;

/**
 * Created by Kirill Listopad.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class MsgMonitorApp {

    public static void main(String[] args) {
        SpringApplication.run(MsgMonitorApp.class, args);
    }
}
