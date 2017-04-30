/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.epitech.serverandroid.services;

import eu.epitech.serverandroid.beans.Dish;
import eu.epitech.serverandroid.beans.Mark;
import eu.epitech.serverandroid.beans.Params;
import eu.epitech.serverandroid.beans.Restaurant;
import eu.epitech.serverandroid.beans.UserClientInfo;
import eu.epitech.serverandroid.dao.DishDao;
import eu.epitech.serverandroid.dao.MarkDao;
import eu.epitech.serverandroid.dao.RestaurantDao;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Vincent RAGOT
 */
@RestController
public class Controller {

    @RequestMapping(value = "/restaurant", method = RequestMethod.POST)
    private String getAllRestaurant(@RequestBody UserClientInfo info) {
        RestaurantDao dao = new RestaurantDao();
        return (dao.getAllRestaurant(info));
    }

    @RequestMapping(value = "/restaurant/add", method = RequestMethod.POST)
    public String addRestaurant(@RequestBody Params<Restaurant> params) {
        RestaurantDao dao = new RestaurantDao();
        return (dao.addRestaurant(params));
    }

    @RequestMapping(value = "/dish", method = RequestMethod.POST)
    public String getAllDish(@RequestBody Params<Restaurant> params) {
        DishDao dao = new DishDao();
        return (dao.getAllDish(params));
    }

    @RequestMapping(value= "/dish/add", method = RequestMethod.POST)
    public String addDish(@RequestBody Params<Dish> params) {
        DishDao dao = new DishDao();
        return (dao.addDish(params));
    }

    @RequestMapping(value= "/mark", method = RequestMethod.POST)
    public String getAllMark(@RequestBody Params<Dish> params) {
        MarkDao dao = new MarkDao();
        return (dao.getAllMark(params));
    }

    @RequestMapping(value= "/mark/add", method = RequestMethod.POST)
    public String addMark(@RequestBody Params<Mark> params) {
        MarkDao dao = new MarkDao();
        return (dao.addMark(params));
    }
}
