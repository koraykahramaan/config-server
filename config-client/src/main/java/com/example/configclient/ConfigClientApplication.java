package com.example.configclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ConfigClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class, args);
    }

}

@RestController
class ValueRestController {

    private final Environment environment;

    public ValueRestController(Environment environment) {
        this.environment = environment;
    }

    @EventListener({ApplicationReadyEvent.class,
                    RefreshScopeRefreshedEvent.class})
    public void onEvent() {
        System.out.println("New value : " + this.readvalue());
    }

    @GetMapping("/value")
    private String readvalue() {
        return this.environment.getProperty("cnj.message");
    }
}
