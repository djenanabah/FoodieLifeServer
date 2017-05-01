package eu.epitech.serverandroid.dao;

import eu.epitech.serverandroid.beans.Dish;
import eu.epitech.serverandroid.beans.Mark;
import eu.epitech.serverandroid.beans.Params;
import eu.epitech.serverandroid.tools.SessionUtil;
import org.hibernate.SessionFactory;

public class MarkDao {

    private final SessionFactory sessionFactory;

    public MarkDao() {
        this.sessionFactory = SessionUtil.getSession();
    }

    public String getAllMark(Params<Dish> params) {
        return null;
    }

    public String addMark(Params<Mark> params) {
        return null;
    }

}
