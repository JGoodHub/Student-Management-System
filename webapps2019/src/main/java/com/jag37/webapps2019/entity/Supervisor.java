
package com.jag37.webapps2019.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

//Queries
@NamedQueries ({
    @NamedQuery(name="getAllSupervisors", query="SELECT s FROM Supervisor s"),
    @NamedQuery(name="getAllSupervisorsWithId", query="SELECT s FROM Supervisor s WHERE s.systemUserInstance.username = :sussexId")
})

@Entity
public class Supervisor implements Serializable {
    
    //Variables

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
        
    @NotNull @OneToOne
    SystemUser systemUserInstance;
    
    @NotNull
    String firstName;    
    
    @NotNull
    String surname;    
    
    @NotNull
    String emailAddress; 
    
    @NotNull
    String department;  
    
    @NotNull
    String telephoneNumber;
    
    @NotNull @OneToMany(mappedBy = "projectSupervisor")
    List<Project> projects;    

    //Methods
    
    public Supervisor () {}

    //Construct the supervisor istances and set their project array to empty
    public Supervisor(SystemUser systemUserInstance, String firstName, String surname, String emailAddress, String department, String telephoneNumber) {
        this.systemUserInstance = systemUserInstance;
        this.firstName = firstName;
        this.surname = surname;
        this.emailAddress = emailAddress;
        this.department = department;
        this.telephoneNumber = telephoneNumber;
        
        projects = new ArrayList<>();
    }
    
    //Add a project to the supervisors array
    public void addProject (Project newProject) {
        projects.add(newProject);
    }

    //Get all the supervisors projects
    public List<Project> getProjects() {
        return projects;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    public String getFullName () {
        return firstName + " " + surname;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.id);
        hash = 13 * hash + Objects.hashCode(this.systemUserInstance);
        hash = 13 * hash + Objects.hashCode(this.firstName);
        hash = 13 * hash + Objects.hashCode(this.surname);
        hash = 13 * hash + Objects.hashCode(this.emailAddress);
        hash = 13 * hash + Objects.hashCode(this.department);
        hash = 13 * hash + Objects.hashCode(this.telephoneNumber);
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
        final Supervisor other = (Supervisor) obj;
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.surname, other.surname)) {
            return false;
        }
        if (!Objects.equals(this.emailAddress, other.emailAddress)) {
            return false;
        }
        if (!Objects.equals(this.department, other.department)) {
            return false;
        }
        if (!Objects.equals(this.telephoneNumber, other.telephoneNumber)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.systemUserInstance, other.systemUserInstance)) {
            return false;
        }
        return true;
    }
    
    

    
    
}
