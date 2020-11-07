package gr.nikolis.models;

import gr.nikolis.qualifiers.JTapiPeer;
import gr.nikolis.qualifiers.ServerHostName;
import gr.nikolis.qualifiers.UserName;
import gr.nikolis.qualifiers.UserPassword;
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
    private final String JTapiExtNumber;

    public JTapiDataImpl(@UserName String userName, @UserPassword String userPassword, @ServerHostName String serverHostName, @JTapiPeer String jTapiPeer, @gr.nikolis.qualifiers.JTapiExtNumber String JTapiExtNumber) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.serverHostName  = serverHostName;
        this.jTapiPeer = jTapiPeer;
        this.JTapiExtNumber = JTapiExtNumber;
    }
}
