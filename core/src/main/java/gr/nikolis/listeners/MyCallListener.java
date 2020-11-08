package gr.nikolis.listeners;

import com.avaya.jtapi.tsapi.adapters.CallListenerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.telephony.CallEvent;

@Component
@Slf4j
public class MyCallListener extends CallListenerAdapter {
    @Override
    public void callActive(CallEvent event) {
        log.info("New Call");
    }
}
