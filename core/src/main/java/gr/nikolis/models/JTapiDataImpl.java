package gr.nikolis.models;

import gr.nikolis.qualifiers.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class JTapiDataImpl implements JTapiData {

    private final String userName;
    private final String userPassword;
    private final String serverHostName;
    private final String jTapiPeer;
    private final String jTapiExtNumber;

    public JTapiDataImpl(@UserName String userName, @UserPassword String userPassword, @ServerHostName String serverHostName, @JTapiPeer String jTapiPeer, @JTapiExtNumber String jTapiExtNumber) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.serverHostName  = serverHostName;
        this.jTapiPeer = jTapiPeer;
        this.jTapiExtNumber = jTapiExtNumber;
    }
}
