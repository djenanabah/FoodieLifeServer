package eu.epitech.serverandroid.dao;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.epitech.serverandroid.beans.UserClientInfo;
import eu.epitech.serverandroid.tools.GoogleTools;

public class ConnectionDao {

    public ConnectionDao() {
    }

    public String getConnection(UserClientInfo info) {
        GoogleTools google = new GoogleTools();
        ObjectMapper mapper = new ObjectMapper();
        String response = null;
        
        if ((info == null) || (info.getToken() == null)) {
            return (null);
        }
        info = google.Connect(info);

        try {
            response = mapper.writeValueAsString(info);
        } catch (JsonProcessingException ex) {
        }
        return (response);
    }

    public String checkConnection(UserClientInfo info) {
        GoogleTools google = new GoogleTools();
        
        if ((info == null) || (info.getToken() == null)) {
            return (null);
        }
        return (google.checkConnect(info));
    }
}
