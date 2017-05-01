/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.epitech.serverandroid.tools;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.people.v1.PeopleService;
import com.google.api.services.people.v1.model.ListConnectionsResponse;
import com.google.api.services.people.v1.model.Person;
import eu.epitech.serverandroid.beans.UserClientInfo;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GoogleTools {

    private static final JacksonFactory jsonFactory = new JacksonFactory();
    private static final HttpTransport transport = new NetHttpTransport();
    private final GoogleIdTokenVerifier verifier;
    private GoogleIdToken idToken;

    public GoogleTools() {
        verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory).build();
        idToken = null;
    }

    public UserClientInfo Connect(UserClientInfo info) {
        try {
            System.out.println("tok: " + info.getToken());
            idToken = verifier.verify(info.getToken());
        } catch (GeneralSecurityException | IOException ex) {
            ex.printStackTrace();
            info.setMessage("500");
            return (info);
        }
        if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();

            info.setName((String) payload.get("name"));
            info.setPictureUrl((String) payload.get("picture"));
            info.seteMail(payload.getEmail());
            info.setMessage("200");
        } else {
            info.setMessage("400");
        }
        return (info);
    }

    public String checkConnect(UserClientInfo info) {
        try {
            idToken = verifier.verify(info.getToken());
        } catch (GeneralSecurityException | IOException ex) {
            ex.printStackTrace();
            return ("500");
        }
        if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();

            if ((!info.getName().equals((String) payload.get("name")))
                    || (!info.geteMail().equals(payload.getEmail()))) {
                return ("400");
            }
        } else {
            return ("400");
        }
        return ("200");
    }
    
    public void getAllFriend(UserClientInfo info) {
        try {
            idToken = verifier.verify(info.getToken());
        } catch (GeneralSecurityException | IOException ex) {
            ex.printStackTrace();
        }
 /*       if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();
            String userId = payload.getSubject();
            ListConnectionsResponse response;
            try {
                ListConnectionsResponse response = PeopleService.people().connections().list("people/me").execute();
                List<Person> connections = response.getConnections();
            } catch (IOException ex) {
                Logger.getLogger(GoogleTools.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            
        }*/
    }
}
