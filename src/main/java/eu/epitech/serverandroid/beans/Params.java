package eu.epitech.serverandroid.beans;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Vincent RAGOT
 * @param <T> Template for any class
 */
public class Params<T> implements Serializable {

    private UserClientInfo user;
    private List<T> list;

    /**
     * Constructor
     */
    public Params() {
    }

    /**
     * @return the user
     */
    public UserClientInfo getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(UserClientInfo user) {
        this.user = user;
    }

    /**
     * @return the list
     */
    public List<T> getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(List<T> list) {
        this.list = list;
    }

}
