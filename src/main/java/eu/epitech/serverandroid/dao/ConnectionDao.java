package eu.epitech.serverandroid.dao;


import eu.epitech.serverandroid.beans.UserClientInfo;
import eu.epitech.serverandroid.tools.GoogleTools;
import eu.epitech.serverandroid.tools.ParserJson;

public class ConnectionDao {

    public ConnectionDao() {
    }

    public String getConnection(UserClientInfo info) {
        GoogleTools google = new GoogleTools();
        ParserJson<UserClientInfo> parser = new ParserJson<>();
        
        if ((info == null) || (info.getToken() == null)) {
            info.setMessage("400");
            parser.setValue(info);
            return (parser.toString());
        }
        parser.setValue(google.Connect(info));
        return (parser.toString());
    }

    public String checkConnection(UserClientInfo info) {
        GoogleTools google = new GoogleTools();
        
        if ((info == null) || (info.getToken() == null)) {
            return ("400");
        }
        return (google.checkConnect(info));
    }
}
