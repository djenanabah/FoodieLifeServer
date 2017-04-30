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

    public ConnectionDao() {
    }

    public UserClientInfo getConnection(UserClientInfo info) {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory).build();
        GoogleIdToken idToken = null;
        
        try {
            idToken = verifier.verify(info.getToken());
        } catch (GeneralSecurityException | IOException ex) {
            info.setMessage("500");
            return(info);
        }
        if (idToken != null) {
            Payload payload = idToken.getPayload();

            String userId = payload.getSubject();
            System.out.println("User ID: " + userId);

            info.setName((String) payload.get("name"));
            info.setPictureUrl((String) payload.get("picture"));
            info.seteMail(payload.getEmail());
            info.setMessage("200");
        } else {
            info.setMessage("400");
        }
        return (info);
    }

}
