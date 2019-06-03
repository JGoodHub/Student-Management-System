package com.jag37.webapps2019.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.NamedQuery;

//Queries
@NamedQuery(name="getAllAdministrators", query="SELECT a FROM Administrator a")
@Entity
public class Administrator implements Serializable {

    //Variables
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToOne
    SystemUser systemUserInstance;
    
    //Methods
    
    public Administrator () {}

    //Construct the administrator entity
    public Administrator(SystemUser systemUserInstance) {
        this.systemUserInstance = systemUserInstance;
    }
       
    //Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SystemUser getSystemUserInstance() {
        return systemUserInstance;
    }

    public void setSystemUserInstance(SystemUser systemUserInstance) {
        this.systemUserInstance = systemUserInstance;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.id);
        hash = 17 * hash + Objects.hashCode(this.systemUserInstance);
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
        final Administrator other = (Administrator) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.systemUserInstance, other.systemUserInstance)) {
            return false;
        }
        return true;
    }
    
    
    
  
    
}
