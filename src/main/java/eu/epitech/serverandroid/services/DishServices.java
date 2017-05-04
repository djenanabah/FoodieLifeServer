package eu.epitech.serverandroid.services;

import eu.epitech.serverandroid.beans.Dish;
import eu.epitech.serverandroid.beans.Params;
import eu.epitech.serverandroid.dao.DishDao;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DishServices {

    @RequestMapping(value = "/dish", method = RequestMethod.POST)
    public String getAllDish(@RequestBody Params<Dish> params) {
        DishDao dao = new DishDao();
        return (dao.getAllDish(params));
    }

    @RequestMapping(value = "/dish/add", method = RequestMethod.POST)
    public String addDish(@RequestBody Params<Dish> params) {
        DishDao dao = new DishDao();
        return (dao.addDish(params));
    }
}
