package com.jag37.webapps2019.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

//Queries
@NamedQueries ({    
    @NamedQuery(name="getAllStudents", query="SELECT s FROM Student s"),
    @NamedQuery(name="findStudentWithSussexId", query="SELECT s FROM Student s WHERE s.systemUserInstance.username = :sussexId")
})

@Entity
public class Student implements Serializable {
    
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
    String courseTitle; 
    
    @OneToOne
    Project studentsProject;
    
    //Methods

    public Student() {}

    //Construct the student entity
    public Student(SystemUser systemUserInstance, String firstName, String surname, String emailAddress, String courseTitle) {
        this.systemUserInstance = systemUserInstance;
        this.firstName = firstName;
        this.surname = surname;
        this.emailAddress = emailAddress;
        this.courseTitle = courseTitle;
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

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public Project getStudentsProject() {
        return studentsProject;
    }
    
    public String getStudentsProjectStatusAsString () {
        if (studentsProject == null) {
            return "No Project Selected";
        } else {
            return studentsProject.getProjectTitle() + "/" + studentsProject.getStatus();
        }
    }

    public void setStudentsProject(Project studentsProject) {
        this.studentsProject = studentsProject;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.systemUserInstance);
        hash = 37 * hash + Objects.hashCode(this.firstName);
        hash = 37 * hash + Objects.hashCode(this.surname);
        hash = 37 * hash + Objects.hashCode(this.emailAddress);
        hash = 37 * hash + Objects.hashCode(this.courseTitle);
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
        final Student other = (Student) obj;
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.surname, other.surname)) {
            return false;
        }
        if (!Objects.equals(this.emailAddress, other.emailAddress)) {
            return false;
        }
        if (!Objects.equals(this.courseTitle, other.courseTitle)) {
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