package com.jag37.webapps2019.jsf;

import com.jag37.webapps2019.ejb.UserServiceBean;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class LoginBean implements Serializable {
    
    //Injected Beans
    
    @EJB
    UserServiceBean userService;
    
    //Variables

    private String username;
    private String password;
    
    //Methods
    
    public LoginBean () {}
    
    //Login using a username and password
    public String login () {
        return userService.login(username, password);
    }
    
    //Logout and redirect to the index
    public String logout () {
        System.out.println("Logging out");
        userService.logout();
        return "logout";
    }   
    
    //Getters and Setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    

}
