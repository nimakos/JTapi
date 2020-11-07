package gr.nikolis.JTapi.listeners;

import com.avaya.jtapi.tsapi.ITsapiCallIDPrivate;
import com.avaya.jtapi.tsapi.LucentAddress;
import com.avaya.jtapi.tsapi.LucentTerminal;
import com.avaya.jtapi.tsapi.adapters.TerminalConnectionListenerAdapter;
import gr.nikolis.JTapi.service.ServiceJTapi;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.telephony.Address;
import javax.telephony.Terminal;
import javax.telephony.TerminalConnectionEvent;
import java.text.DateFormat;
import java.util.Date;

@Component
@Slf4j
public class MyCallListener extends TerminalConnectionListenerAdapter {

    @Autowired
    ServiceJTapi serviceJTapi;

    @Override
    public void terminalConnectionRinging(TerminalConnectionEvent event) {
        try {
            Address calledAddress = event.getCall().getConnections()[1].getAddress();
            if (calledAddress.equals(serviceJTapi.getMyAddress()))
                handleIncomingCall(event);
            else if ((serviceJTapi.getCalledBackHash()).get(((ITsapiCallIDPrivate) event.getCall()).getTsapiCallID()) == null)
                handleOutgoingCall(event);
        } catch (Exception e) {
            log.error("Exception in terminal connection ringing event");
        }
    }

    /**
     * Handle an Incoming call
     *
     * @param event The event that is being triggered
     */
    public void handleIncomingCall(@NonNull TerminalConnectionEvent event) {
        Address callingAddress, calledAddress;
        Terminal callingTerminal;
        String name = null;
        Date d = new Date();

        callingTerminal = event.getTerminalConnection().getTerminal();
        callingAddress = event.getCall().getConnections()[0].getAddress();
        calledAddress = event.getCall().getConnections()[1].getAddress();

        if (callingAddress != null) {
            // verify that callingAddress is not our address, otherwise, it is us
            // initiating the call
            if (!(callingAddress.equals(serviceJTapi.getMyAddress()))) {
                // get called number information
                if (callingTerminal instanceof LucentTerminal) {
                    try {
                        name = ((LucentAddress) callingAddress).getDirectoryName();
                    } catch (Exception e) {
                        log.error(e.getMessage());
                    }
                }
                if (name == null)
                    name = callingAddress.getName();
            }
            log.info(DateFormat.getDateTimeInstance().format(d) + " Call received from: " + name);
        } else
            log.info(DateFormat.getDateTimeInstance().format(d) + " Call received from: <Unknown>");
    }

    /**
     * Handle an Outgoing call
     *
     * @param event The event that is being triggered
     */
    public void handleOutgoingCall(TerminalConnectionEvent event) {
        Address calledAddress;
        String calledNumber = "<Unknown>";
        String name;
        calledAddress = event.getCall().getConnections()[1].getAddress();
        try {
            // get called number information
            if (calledAddress != null)
                calledNumber = calledAddress.getName();

            // get called number information
            if (calledAddress instanceof LucentAddress) {
                try {
                    name = ((LucentAddress) calledAddress).getDirectoryName();
                    log.info("Call : " + name + "Call Number " + calledNumber);
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            }
        } catch (Exception e) {
            log.error("handleOutgoingCall() caught " + e + "\n");
            log.error("Message: " + e.getMessage() + "\n\n");
        }
    }
}
