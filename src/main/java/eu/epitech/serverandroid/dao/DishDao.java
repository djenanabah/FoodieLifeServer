package eu.epitech.serverandroid.dao;

import eu.epitech.serverandroid.beans.Dish;
import eu.epitech.serverandroid.beans.Params;
import eu.epitech.serverandroid.beans.Restaurant;
import eu.epitech.serverandroid.tools.SessionUtil;
import org.hibernate.SessionFactory;

public class DishDao {

    private final SessionFactory sessionFactory;

    public DishDao() {
        this.sessionFactory = SessionUtil.getSession();
    }

    public String getAllDish(Params<Restaurant> params) {
        return null;
    }

    public String addDish(Params<Dish> params) {
        return null;
    }

}
