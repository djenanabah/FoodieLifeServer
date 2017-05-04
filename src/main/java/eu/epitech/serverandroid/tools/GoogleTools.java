/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.epitech.serverandroid.tools;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.people.v1.PeopleService;
import com.google.api.services.people.v1.model.ListConnectionsResponse;
import com.google.api.services.people.v1.model.Person;
import eu.epitech.serverandroid.beans.UserClientInfo;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vincent RAGOT
 */
public class GoogleTools {

    private static final JacksonFactory jsonFactory = new JacksonFactory();
    private static final HttpTransport transport = new NetHttpTransport();
    private final GoogleIdTokenVerifier verifier;
    private GoogleIdToken idToken;

    /**
     * Constructor
     */
    public GoogleTools() {
        verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory).
                build();
        idToken = null;
    }

    /**
     *
     * @param info contains idToken
     * @return all info about user
     */
    public UserClientInfo Connect(UserClientInfo info) {
        try {
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
            info.setMessage("401");
        }
        return (info);
    }

    /**
     *
     * @param info contains user's info
     * @return String response if connection is available or not 
     */
    public String checkConnect(UserClientInfo info) {
        try {
            idToken = verifier.verify(info.getToken());
        } catch (GeneralSecurityException | IOException ex) {
            ex.printStackTrace();
            return ("500");
        }
        if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();
System.out.println("name: " + info.getName() + ", attendu: " + (String) payload.get("name"));
                System.out.println("email: " + info.geteMail() + ", attendu: " + payload.getEmail());
            if (info.getName().equals((String) payload.get("name"))){
                return ("200");
            } else {
                return ("402");
            }
        } else {
            return ("401");
        }
    }
    
    /**
     *
     * @param info contains user's info
     * @return list off all friens
     */
    public ArrayList<String> getAllFriends(UserClientInfo info) {
        ArrayList<String> list = new ArrayList<>();
        
        try {
            idToken = verifier.verify(info.getToken());
        } catch (GeneralSecurityException | IOException ex) {
            ex.printStackTrace();
            return (null);
        }
        if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();
            String userId = payload.getSubject();
            ListConnectionsResponse response;
            try {
                PeopleService peopleService = new PeopleService.
                        Builder(transport, jsonFactory,
                                (HttpRequestInitializer) verifier).build();
                response = peopleService.people().connections().
                        list(userId).execute();
                List<Person> connections = response.getConnections();
                connections.forEach((person) -> {
                    list.add(person.getNames().get(0).getDisplayName());
                });
            } catch (IOException ex) {
                ex.printStackTrace();
                return (null);
            }
        } else {
            return (null);
        }
        return (list);
    }
}
