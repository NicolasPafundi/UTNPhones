package com.utn.TPFinal.session;

import com.utn.TPFinal.model.entities.User;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class SessionManager {

    Map<String, Session> sessionMap = new Hashtable<>();

    int sesionExpiration = 1000;

    public String createSession(User user) {
        String token = UUID.randomUUID().toString();
        sessionMap.put(token, new Session(token, user, new Date(System.currentTimeMillis())));
        return token;
    }

    public Session getSession(String token) {
        Session session = sessionMap.get(token);
        if (session!=null) {
            session.setLastAction(new Date(System.currentTimeMillis()));
        }
        return session;
    }

    public void removeSession(String token) {
        sessionMap.remove(token);
    }

    public void expireSessions() {
        for (String k : sessionMap.keySet()) {
            Session v = sessionMap.get(k);
            if (v.getLastAction().getTime() + (sesionExpiration * 1000) < System.currentTimeMillis()) {
                System.out.println("Expiring session " + k);
                sessionMap.remove(k);
            }
        }
    }

    public User getCurrentUser(String token) {
        Session session =  getSession(token);
        if(session != null){
            return session.getLoggedUser();
        }else{
            return null;
        }
    }
}