package gr.nikolis.JTapi.controller;

import com.avaya.jtapi.tsapi.adapters.CallListenerAdapter;
import gr.nikolis.JTapi.constants.mappings.HomeMappings;
import gr.nikolis.JTapi.constants.views.ViewNames;
import gr.nikolis.JTapi.service.ServiceJTapi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController extends CallListenerAdapter {

    @Autowired
    ServiceJTapi serviceJTapi;

    // http://localhost:8888 or http://localhost:8888/home
    @GetMapping({HomeMappings.HOME, HomeMappings.HOME_DEFAULT})
    public String home() {
        //serviceJTapi.login();

        return ViewNames.HOME;
    }

}
