package com.jag37.webapps2019.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import com.jag37.webapps2019.entity.Log;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;


@Stateless
public class LogServiceBean {
    
    //Injected Beans
    
    @PersistenceContext
    EntityManager entityManager;
    
    //Methods
    
    public LogServiceBean () {}
    
    //Adds a new log to the system that records the time and date of the event, the username of the user logged in at the time and their class role
    public void AddNewLog (String message) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String username = request.getUserPrincipal().getName();
        
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime localDataTime = LocalDateTime.now();
        
        String userClass = "";
        
        Log newLog = new Log(userClass, username, dateTimeFormatter.format(localDataTime), message);
        entityManager.persist(newLog);        
    }

    //Call to the business logic to retrieve all the logs for a given user name
    public List<Log> getAllLogsForUser (String username)  {
        return (List<Log>) entityManager.createNamedQuery("getAllLogsForUserName").setParameter("username", username).getResultList();
    }
    
    //Call to the business logic to retrieve all logs
    public List<Log> getAllLogs () {
        return (List<Log>) entityManager.createNamedQuery("getAllLogs").getResultList();
    }
    
}
