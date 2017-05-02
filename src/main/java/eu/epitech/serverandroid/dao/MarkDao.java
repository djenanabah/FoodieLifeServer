package eu.epitech.serverandroid.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.epitech.serverandroid.beans.Mark;
import eu.epitech.serverandroid.beans.Params;
import eu.epitech.serverandroid.beans.Response;
import eu.epitech.serverandroid.tools.SessionUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Vincent RAGOT
 */
public class MarkDao {

    private final SessionFactory sessionFactory;

    /**
     * Constructor
     */
    public MarkDao() {
        this.sessionFactory = SessionUtil.getSession();
    }

    /**
     *
     * @param params contains user's info and object of class Mark with idDish
     * @return JSON's String contains list of all mark for a dish
     */
    public String getAllMark(Params<Mark> params) {
        ConnectionDao cd = new ConnectionDao();
        Response<Mark> response = new Response<>();
        ObjectMapper mapper = new ObjectMapper();
        String resp;

        if ((params == null) || (params.getUser() == null) ||
                (params.getUser().getToken() == null)) {
            response.setMessage("400");
        } else {
            response.setMessage(cd.checkConnection(params.getUser()));
            try {
                Session session = sessionFactory.openSession();
                Query query = session.
                        createQuery("from Mark where idDish = :id");
                query.setString("id",
                        Integer.toString(params.getList().get(0).getIdDish()));
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
     * @param params contains user's info and object of class Mark to insert
     * @return JSON's String contains response of insert
     */
    public String addMark(Params<Mark> params) {
        ConnectionDao cd = new ConnectionDao();
        Response<Mark> response = new Response<>();
        ObjectMapper mapper = new ObjectMapper();
        String resp;

        if ((params == null) || (params.getUser() == null) ||
                (params.getUser().getToken() == null)) {
            response.setMessage("400");
        } else {
            response.setMessage(cd.checkConnection(params.getUser()));
            try {
                Session session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();
                session.save(params.getList().get(0));
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

    /**
     *
     * @param friend String respresent a friend name
     * @return list off all dish of a friend
     */
    public List<Integer> getAllDishFriends(String friend) {
        List<Mark> list = new ArrayList<>();
        List<Integer> response = new ArrayList<>();
        try {
            Session session = sessionFactory.openSession();
            Query query = session.createQuery("from Mark where user = :user");
            query.setString("user", friend);
            list = query.list();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            return (null);
        }
        for (Mark mark : list) {
            response.add(mark.getIdDish());
        }
        return (response);
    }

}
