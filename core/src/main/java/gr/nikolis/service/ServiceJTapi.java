package gr.nikolis.service;

import com.avaya.jtapi.tsapi.adapters.ProviderListenerAdapter;
import gr.nikolis.listeners.MyCallListenerAdapter;
import gr.nikolis.listeners.MyTerminalCallListenerAdapter;
import gr.nikolis.models.JTapiData;
import gr.nikolis.qualifiers.JTapiPeer;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.telephony.*;
import java.util.HashMap;

@Service
@Slf4j
public class ServiceJTapi extends ProviderListenerAdapter {

    @Autowired
    JtapiPeer jtapiPeer;
    @Autowired
    MyTerminalCallListenerAdapter myTerminalCallListenerAdapter;
    @Autowired
    MyCallListenerAdapter myCallListenerAdapter;

    JTapiData jTapiData;
    Provider provider;
    Terminal myTerminal;
    @Getter
    private Address myAddress;

    @Getter
    private final HashMap<Integer, Integer> answeredHash = new HashMap<>(10);
    @Getter
    private final HashMap<Integer, Integer> calledBackHash = new HashMap<>(10);

    @Autowired
    public ServiceJTapi(JTapiData jTapiData) {
        this.jTapiData = jTapiData;
        log.info("Starting API");
    }

    /***
     *
     * @return The JtapiPeer object using JtapiPeerFactory class
     */
    @Bean
    public JtapiPeer getJtapiPeer(@JTapiPeer String jTapiPeer) {
        JtapiPeer peer = null;
        try {
            peer = JtapiPeerFactory.getJtapiPeer(null);
        } catch (Exception ex) {
            try {
                return JtapiPeerFactory.getJtapiPeer(jTapiPeer);
            } catch (JtapiPeerUnavailableException e) {
                log.error("Error: JtapiPeer could not be created.  Verify your Jtapi client install. :" + e.getMessage());
            }
        }
        return peer;
    }

    /**
     * Make the login to the server
     */
    @PostConstruct
    public synchronized void login() {
        try {
            provider = jtapiPeer.getProvider(getServiceName() + ";login=" + jTapiData.getUserName() + ";passwd=" + jTapiData.getUserPassword() + ";servers=" + jTapiData.getServerHostName());
            provider.addProviderListener(this);
            synchronized (this) {
                notify();
                wait();
            }
            log.info("Provider is in service.\\n\\n");
        } catch (Exception ex) {
            log.error("Exception during getting provider: \" + ex");
            return;
        }

        try {
            try {
                myAddress = provider.getAddress(jTapiData.getJTapiExtNumber());
                myTerminal = provider.getTerminal(jTapiData.getJTapiExtNumber());
            } catch (Exception ex) {
                log.error("Please verify that the dialing extension number is correct.");
            }
            myTerminal.addCallListener(myTerminalCallListenerAdapter);
            myAddress.addCallListener(myCallListenerAdapter);
        } catch (Exception ex) {
            log.error("login() caught " + ex + "Message: " + ex.getMessage());
        }
    }

    /**
     * Get service method services supported by the JtapiPeer implementation
     *
     * @return Supported Services
     */
    public String[] getServices() {
        String[] services;
        try {
            services = jtapiPeer.getServices();
            if (services == null) {
                log.error("Unable to obtain the services list from JTAPI peer.\n");
                System.exit(0);
            } else
                return services;
        } catch (Exception ex) {
            log.error("Exception during getting services: " + ex);
        }
        return null;
    }

    /**
     * Get the service name
     *
     * @return The service name
     */
    public String getServiceName() {
        return getServices()[0];
    }
}
