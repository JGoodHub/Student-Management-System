package com.jag37.webapps2019.jsf;

import com.jag37.webapps2019.ejb.UserServiceBean;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class RegisterBean {

    //Injected Beans
    
    @EJB
    UserServiceBean userService;
    
    //Variables
    
    //Shared user
    String sussexId;
    String password;
    String firstName;
    String surname;
    String emailAddress;
        
    //Supervisor only
    String department;
    String telephoneNumber;
    
    //Student only
    String courseTitle;
    
    //Methods

    public RegisterBean() {}
    
    //Register a new admin in the back end
    public String registerAdministrator() {    
        return userService.registerAdministrator(sussexId, password);
    }
    
    //Register a nw supervisor in the backend
    public String registerSupervisor() {    
        return userService.registerSupervisor(sussexId, password, firstName, surname, emailAddress, department, telephoneNumber);
    }
    
    //Register a new student in the back end
    public String registerStudent() {    
        return userService.registerStudent(sussexId, password, firstName, surname, emailAddress, courseTitle);
    }
    
    //Getters and Setters

    public String getSussexId() {
        return sussexId;
    }

    public void setSussexId(String sussexId) {
        this.sussexId = sussexId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }
    
    
    
}
