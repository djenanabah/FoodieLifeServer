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
        MarkDao md = new MarkDao();
        DishDao dd = new DishDao();
        Response<Restaurant> response = new Response<>();
        GoogleTools google = new GoogleTools();
        ArrayList<String> listFriends;
        ArrayList<Integer> listRestaurantFriends = new ArrayList<>();
        ArrayList<Integer> listDishFriends = new ArrayList<>();
        ArrayList<Restaurant> listResponse = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        String resp;
        
        if ((info == null) || (info.getToken() == null)) {
            response.setMessage("400");
        } else {
            response.setMessage(cd.checkConnection(info));
            if (!response.getMessage().equals("200")) {
                listFriends = google.getAllFriends(info);
                if ((listFriends != null) && (listFriends.size() > 0)) {
                    for (String friend : listFriends) {
                        listDishFriends.addAll(md.getAllDishFriends(friend));
                    }
                    listDishFriends = clearList(listDishFriends);
                    for (int dish : listDishFriends) {
                        listRestaurantFriends.add(dd.getRestaurantDish(dish));
                    }
                    listRestaurantFriends = clearList(listRestaurantFriends);
                    for (int id : listRestaurantFriends) {
                        listResponse.add(getRestaurant(id));
                    }
                    response.setList(listResponse);
                } else {
                    response.setMessage("403");
                }
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
     * @param params contains user's info and object of class Restaurant to insert
     * @return JSON's String contains response of insert
     */
    public String addRestaurant(Params<Restaurant> params) {
        ConnectionDao cd = new ConnectionDao();
        Response<Restaurant> response = new Response<>();
        ObjectMapper mapper = new ObjectMapper();
        String resp;
        
        if ((params == null) || (params.getUser() == null) ||
                (params.getUser().getToken() == null)) {
            response.setMessage("400");
        } else {
            response.setMessage(cd.checkConnection(params.getUser()));
            if (!response.getMessage().equals("200")) {
                try {
                    Session session = sessionFactory.openSession();
                    Transaction transaction = session.beginTransaction();
                    session.save(params.getValue());
                    transaction.commit();
                    session.close();
                } catch (HibernateException e) {
                    e.printStackTrace();
                    response.setMessage("500");
                }
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
     * @param list contains to clear (remove duplicate)
     * @return list cleared
     */
    private ArrayList<Integer> clearList(ArrayList<Integer> list) {
        Set<Integer> hs = new HashSet<>();
        hs.addAll(list);
        list.clear();
        list.addAll(hs);
        return (list);
    }

    /**
     *
     * @param id id of a restaurant
     * @return Object of class Restaurant
     */
    private Restaurant getRestaurant(int id) {
        Restaurant response;
        try {
            Session session = sessionFactory.openSession();
            Query query = session.
                    createQuery("from Restaurant where idRestaurant = :id");
            query.setString("id", Integer.toString(id));
            response = (Restaurant) query.list().get(0);
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            return (null);
        }
        return response;
    }
    
}
