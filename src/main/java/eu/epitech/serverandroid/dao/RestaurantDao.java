package eu.epitech.serverandroid.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.epitech.serverandroid.tools.SessionUtil;
import eu.epitech.serverandroid.beans.Params;
import eu.epitech.serverandroid.beans.Response;
import eu.epitech.serverandroid.beans.Restaurant;
import eu.epitech.serverandroid.beans.UserClientInfo;
import eu.epitech.serverandroid.tools.GoogleTools;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Vincent RAGOT
 */
public class RestaurantDao {

    private final SessionFactory sessionFactory;

    /**
     * Constructor
     */
    public RestaurantDao() {
        this.sessionFactory = SessionUtil.getSession();
    }

    /**
     *
     * @param info contains user's info
     * @return JSON's String contains list of all restaurant for a user
     */
    public String getAllRestaurant(UserClientInfo info) {
        ConnectionDao cd = new ConnectionDao();
        Response<Restaurant> response = new Response<>();
        ObjectMapper mapper = new ObjectMapper();
        String resp;

        if ((info == null) || (info.getToken() == null)) {
            response.setMessage("400");
        } else {
            response.setMessage(cd.checkConnection(info));
            if (response.getMessage().equals("200")) {
                try {
                    Session session = sessionFactory.openSession();
                    Query query = session.
                            createQuery("from Restaurant");
                    response.setList(query.list());
                    session.close();
                } catch (HibernateException e) {
                    e.printStackTrace();
                    response.setMessage("500");
                }
            } else {
                response.setMessage("403");
            }
        }
        try {
            resp = mapper.writeValueAsString(response);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
            return (null);
        }
        return (resp);
    }

    /**
     *
     * @param params contains user's info and object of class Restaurant to
     * insert
     * @return JSON's String contains response of insert
     */
    public String addRestaurant(Params<Restaurant> params) {
        ConnectionDao cd = new ConnectionDao();
        Response<Restaurant> response = new Response<>();
        ObjectMapper mapper = new ObjectMapper();
        String resp;

        if ((params.getUser() == null) ||
                (params.getUser().getToken() == null)) {
            response.setMessage("400");
        } else {
            response.setMessage(cd.checkConnection(params.getUser()));
            if (response.getMessage().equals("200")) {
                try {
                    Session session = sessionFactory.openSession();
                    Transaction transaction = session.beginTransaction();
                    session.save(params);
                    transaction.commit();
                    session.close();
                } catch (HibernateException e) {
                    e.printStackTrace();
                    response.setMessage("500");
                }
            } else {
                response.setMessage("403");
            }
        }
        try {
            resp = mapper.writeValueAsString(response);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
            return (null);
        }
        return (resp);
    }
}
