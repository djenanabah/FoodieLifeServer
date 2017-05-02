package eu.epitech.serverandroid.services;

import eu.epitech.serverandroid.beans.Params;
import eu.epitech.serverandroid.beans.Restaurant;
import eu.epitech.serverandroid.beans.UserClientInfo;
import eu.epitech.serverandroid.dao.RestaurantDao;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestaurantServices {

    @RequestMapping(value = "/restaurant", method = RequestMethod.POST)
    private String getAllRestaurant(@ModelAttribute UserClientInfo info) {
        RestaurantDao dao = new RestaurantDao();
        return (dao.getAllRestaurant(info));
    }

    @RequestMapping(value = "/restaurant/add", method = RequestMethod.POST)
    public String addRestaurant(@ModelAttribute Params<Restaurant> params) {
        RestaurantDao dao = new RestaurantDao();
        return (dao.addRestaurant(params));
    }
}
