/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.epitech.serverandroid.beans;

/**
 *
 * @author Vincent RAGOT
 */
public class UserClientInfo {
    
    private String eMail;
    private String token;

    public UserClientInfo() {
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
}
