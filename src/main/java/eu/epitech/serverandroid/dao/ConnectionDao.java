package eu.epitech.serverandroid.dao;


import eu.epitech.serverandroid.beans.UserClientInfo;
import eu.epitech.serverandroid.tools.GoogleTools;

public class ConnectionDao {

    public ConnectionDao() {
    }

    public UserClientInfo getConnection(UserClientInfo info) {
        GoogleTools google = new GoogleTools();
        
        if ((info == null) || (info.getToken() == null)) {
            info.setMessage("400");
            return (info);
        }
        return (google.Connect(info));
    }

    public String checkConnection(UserClientInfo info) {
        GoogleTools google = new GoogleTools();
        
        if ((info == null) || (info.getToken() == null)) {
            return ("400");
        }
        return (google.checkConnect(info));
    }
}
