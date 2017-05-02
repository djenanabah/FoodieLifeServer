package eu.epitech.serverandroid.services;

import eu.epitech.serverandroid.beans.UserClientInfo;
import eu.epitech.serverandroid.dao.ConnectionDao;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainServices {
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    private String connection(@ModelAttribute UserClientInfo info) {
        ConnectionDao dao = new ConnectionDao();
        return (dao.getConnection(info));
    }
}
