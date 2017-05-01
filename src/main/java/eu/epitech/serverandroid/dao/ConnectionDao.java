package eu.epitech.serverandroid.dao;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import eu.epitech.serverandroid.beans.UserClientInfo;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class ConnectionDao {

    private static final JacksonFactory jsonFactory = new JacksonFactory();
    private static final HttpTransport transport = new NetHttpTransport();
    private GoogleIdTokenVerifier verifier;
    private GoogleIdToken idToken;

    public ConnectionDao() {
        verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory).build();
        idToken = null;
    }

    public UserClientInfo getConnection(UserClientInfo info) {
        try {
            idToken = verifier.verify(info.getToken());
        } catch (GeneralSecurityException | IOException ex) {
            ex.printStackTrace();
            info.setMessage("500");
            return (info);
        }
        if (idToken != null) {
            Payload payload = idToken.getPayload();

            info.setName((String) payload.get("name"));
            info.setPictureUrl((String) payload.get("picture"));
            info.seteMail(payload.getEmail());
            info.setMessage("200");
        } else {
            info.setMessage("400");
        }
        return (info);
    }

    public String checkConnection(UserClientInfo info) {
        try {
            idToken = verifier.verify(info.getToken());
        } catch (GeneralSecurityException | IOException ex) {
            ex.printStackTrace();
            return ("500");
        }
        if (idToken != null) {
            Payload payload = idToken.getPayload();

            if ((!info.getName().equals((String) payload.get("name")))
                    || (!info.geteMail().equals(payload.getEmail()))) {
                return ("400");
            }
        } else {
            return ("400");
        }
        return ("200");
    }
}
