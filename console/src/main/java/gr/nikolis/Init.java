package gr.nikolis;

import gr.nikolis.service.ServiceJTapi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Init {
    @Autowired
    ServiceJTapi serviceJTapi;

    @EventListener(ContextRefreshedEvent.class)
    public void start() {
        log.info("Console Main started");
    }
}
