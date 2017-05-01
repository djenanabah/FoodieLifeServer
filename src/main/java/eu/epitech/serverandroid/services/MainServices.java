package eu.epitech.serverandroid.services;

import eu.epitech.serverandroid.beans.UserClientInfo;
import eu.epitech.serverandroid.dao.ConnectionDao;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainServices {
    
    @RequestMapping(value = "/", method = RequestMethod.POST)
    private String test() {
        return ("200");
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    private String connection(@RequestBody UserClientInfo info) {
        System.out.println("token :" + info.getToken());
        ConnectionDao dao = new ConnectionDao();
        return (dao.getConnection(info));
    }
    
}
