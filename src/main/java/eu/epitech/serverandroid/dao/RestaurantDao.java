package eu.epitech.serverandroid.dao;

import eu.epitech.serverandroid.tools.SessionUtil;
import eu.epitech.serverandroid.beans.Params;
import eu.epitech.serverandroid.beans.Restaurant;
import eu.epitech.serverandroid.beans.UserClientInfo;
import org.hibernate.SessionFactory;

public class RestaurantDao {

    private final SessionFactory sessionFactory;
    
    public RestaurantDao() {
        this.sessionFactory = SessionUtil.getSession();
    }
    
    public String getAllRestaurant(UserClientInfo info) {
        return null;
    }
        
    public String addRestaurant(Params<Restaurant> params) {
        return null;
    }
    
}
