package eu.epitech.serverandroid.dao;

import eu.epitech.serverandroid.tools.SessionUtil;
import eu.epitech.serverandroid.beans.Params;
import eu.epitech.serverandroid.beans.Response;
import eu.epitech.serverandroid.beans.Restaurant;
import eu.epitech.serverandroid.beans.UserClientInfo;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class RestaurantDao {

    private final SessionFactory sessionFactory;

    public RestaurantDao() {
        this.sessionFactory = SessionUtil.getSession();
    }

    public Response<Restaurant> getAllRestaurant(UserClientInfo info) {
        ConnectionDao cd = new ConnectionDao();
        Response<Restaurant> response = new Response<>();
        if ((info == null) || (info.getToken() == null)) {
            response.setMessage("400");
            return response;
        }
        response.setMessage(cd.checkConnection(info));
        if (!response.getMessage().equals("200")) {
            getAllFriends();
            // for all friends -> get all mark -> get all dish -> get all restaurant
        }
        return response;
    }

    public Response<Restaurant> addRestaurant(Params<Restaurant> params) {
        ConnectionDao cd = new ConnectionDao();
        Response<Restaurant> response = new Response<>();
        if ((params.getUser() == null) || (params.getUser().getToken() == null)) {
            response.setMessage("400");
            return response;
        }
        response.setMessage(cd.checkConnection(params.getUser()));
        if (!response.getMessage().equals("200")) {
            Restaurant rest = params.getList().get(0);
            try {
                Session session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();
                session.save(rest);
                transaction.commit();
                session.close();
            } catch (HibernateException e) {
                e.printStackTrace();
                response.setMessage("200");
                return response;
            }
        }
        return response;
    }

    private void getAllFriends() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
