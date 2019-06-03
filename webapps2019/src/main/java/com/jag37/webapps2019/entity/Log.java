package com.jag37.webapps2019.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;

//Queries
@NamedQueries ({
    @NamedQuery(name="getAllLogs", query="SELECT l FROM Log l"),
    @NamedQuery(name="getAllLogsForUserName", query="SELECT l FROM Log l WHERE l.username = :username")    
})

@Entity
public class Log implements Serializable {

    //Variables
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotNull
    String userClass;
    
    @NotNull
    String username;
    
    @NotNull
    String timeStamp;
    
    @NotNull
    String message;
    
    //Methods
    
    public Log () {}
    
    //Construct the log entity
    public Log(String userClass, String username, String timeStamp, String message) {
        this.userClass = userClass;
        this.username = username;
        this.timeStamp = timeStamp;
        this.message = message;
    }
    
    //Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserClass() {
        return userClass;
    }

    public void setUserClass(String userClass) {
        this.userClass = userClass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.userClass);
        hash = 79 * hash + Objects.hashCode(this.username);
        hash = 79 * hash + Objects.hashCode(this.timeStamp);
        hash = 79 * hash + Objects.hashCode(this.message);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Log other = (Log) obj;
        if (!Objects.equals(this.userClass, other.userClass)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.timeStamp, other.timeStamp)) {
            return false;
        }
        if (!Objects.equals(this.message, other.message)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    
}
