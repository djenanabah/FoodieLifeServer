/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.epitech.serverandroid.beans;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Vincent RAGOT
 * @param <T>
 */
public class Params<T> implements Serializable {

    private UserClientInfo user;
    private List<T> list;

    public Params() {
    }

    public UserClientInfo getUser() {
        return user;
    }

    public void setUser(UserClientInfo user) {
        this.user = user;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
