package eu.epitech.serverandroid.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.epitech.serverandroid.beans.Dish;
import eu.epitech.serverandroid.beans.Params;
import eu.epitech.serverandroid.beans.Response;
import eu.epitech.serverandroid.tools.SessionUtil;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Vincent RAGOT
 */
public class DishDao {

    private final SessionFactory sessionFactory;

    /**
     * Constructor
     */
    public DishDao() {
        this.sessionFactory = SessionUtil.getSession();
    }

    /**
     *
     * @param params contains user's info and object of class Dish with idRestaurant
     * @return JSON's String contains list of all dish for a restaurant
     */
    public String getAllDish(Params<Dish> params) {
        ConnectionDao cd = new ConnectionDao();
        Response<Dish> response = new Response<>();
        ObjectMapper mapper = new ObjectMapper();
        String resp;

        if ((params == null) || (params.getUser() == null)
                || (params.getUser().getToken() == null)) {
            response.setMessage("400");
        } else {
            response.setMessage(cd.checkConnection(params.getUser()));
            try {
                Session session = sessionFactory.openSession();
                Query query = session.
                        createQuery("from Dish where idRestaurant = :id");
                query.setString("id",
                        Integer.toString(
                                params.getValue().getIdRestaurant()));
                response.setList(query.list());
                session.close();
            } catch (Exception e) {
                e.printStackTrace();
                response.setMessage("500");
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
     * @param params contains user's info and object of class Dish to insert
     * @return JSON's String contains response of insert
     */
    public String addDish(Params<Dish> params) {
        ConnectionDao cd = new ConnectionDao();
        Response<Dish> response = new Response<>();
        ObjectMapper mapper = new ObjectMapper();
        String resp;

        if ((params == null) || (params.getUser() == null)
                || (params.getUser().getToken() == null)) {
            response.setMessage("400");
        } else {
            response.setMessage(cd.checkConnection(params.getUser()));
            try {
                Session session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();
                session.save(params.getValue());
                transaction.commit();
                session.close();
            } catch (Exception e) {
                e.printStackTrace();
                response.setMessage("500");
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
