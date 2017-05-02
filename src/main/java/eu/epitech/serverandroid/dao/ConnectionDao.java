package eu.epitech.serverandroid.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.epitech.serverandroid.beans.UserClientInfo;
import eu.epitech.serverandroid.tools.GoogleTools;

/**
 *
 * @author Vincent RAGOT
 */
public class ConnectionDao {

    /**
     * Constructor
     */
    public ConnectionDao() {
    }

    /**
     *
     * @param info contains user's information
     * @return JSON's String contains response for the connection
     */
    public String getConnection(UserClientInfo info) {
        GoogleTools google = new GoogleTools();
        ObjectMapper mapper = new ObjectMapper();
        String response = null;

        if (info == null) {
            return (null);
        } else if (info.getToken() == null) {
            info.setMessage(("400"));
        } else {
            info = google.Connect(info);
        }
        try {
            response = mapper.writeValueAsString(info);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
            return (null);
        }
        return (response);
    }

    /**
     *
     * @param info contains user's information
     * @return String contains if connection is available or not
     */
    public String checkConnection(UserClientInfo info) {
        GoogleTools google = new GoogleTools();

        if ((info == null) || (info.getToken() == null)) {
            return ("400");
        }
        return (google.checkConnect(info));
    }
}
