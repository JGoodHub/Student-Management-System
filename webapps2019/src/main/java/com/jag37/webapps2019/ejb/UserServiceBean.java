package com.jag37.webapps2019.ejb;

import com.jag37.webapps2019.entity.Administrator;
import com.jag37.webapps2019.entity.Student;
import com.jag37.webapps2019.entity.Supervisor;
import com.jag37.webapps2019.entity.SystemUser;
import com.jag37.webapps2019.entity.SystemUserGroup;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Stateless
public class UserServiceBean {
    
    //Injected Beans
    
    @PersistenceContext
    EntityManager entityManager;
    
    @EJB
    LogServiceBean loggerBean;
    
    //Methods

    public UserServiceBean() {}
    
    //Register a new administrator into the system
    public String registerAdministrator (String sussexId, String password) {
        //Create that users systemUser instance
        SystemUser newSystemUser = registerSystemUser(sussexId, password, "administrators");
        if (newSystemUser != null) {
            //Persist the new instance into the database
            Administrator newAdministrator = new Administrator(newSystemUser);
            entityManager.persist(newAdministrator);
            loggerBean.AddNewLog("New Admin regsitered into the system: " + sussexId);
                        
            return "registrationSuccess";
        } else {
            return "registrationFailed";
        }
    }
    
    //Register a new supervisor in the database
    public String registerSupervisor (String sussexId, String password, String firstName, String surname, String emailAddress, String department, String telephoneNumber) {
        //Create that users systemUser instance
        SystemUser newSystemUser = registerSystemUser(sussexId, password, "supervisors");
        if (newSystemUser != null) {
            //Persist the new instance into the database
            Supervisor newSupervisor = new Supervisor(newSystemUser, firstName, surname, emailAddress, department, telephoneNumber);
            entityManager.persist(newSupervisor);
            loggerBean.AddNewLog("New Supervisor regsitered into the system: " + sussexId);
            
            return "registrationSuccess";
        } else {
            return "registrationFailed";
        }
    }
    
    //Register a new student into the database
    public String registerStudent (String sussexId, String password, String firstName, String surname, String emailAddress, String courseTitle) {
        //Create that users systemUser instance
        SystemUser newSystemUser = registerSystemUser(sussexId, password, "students");
        if (newSystemUser != null) {
            //Persist the new instance into the database
            Student newStudent = new Student(newSystemUser, firstName, surname, emailAddress, courseTitle);
            entityManager.persist(newStudent);
            loggerBean.AddNewLog("New Student regsitered into the system: " + sussexId);
            
            return "registrationSuccess";
        } else {
            return "registrationFailed";
        }
    }

    //Create the base system user and system user group instances behind every user
    public SystemUser registerSystemUser(String sussexId, String password, String userGroup) {              
        try {
            //Create the entity instances
            SystemUser systemUser;
            SystemUserGroup systemUserGroup;

            //Hash the users password using the SHA-256 hashing algorithm
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String passwd = password;
            md.update(passwd.getBytes("UTF-8"));
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < digest.length; i++) {
                sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
            }
            String hashedPassword = sb.toString();
            
            //Pass the variables into the system user instances
            systemUser = new SystemUser(sussexId, hashedPassword);
            systemUserGroup = new SystemUserGroup(sussexId, userGroup);

            //Store the new users in the database
            entityManager.persist(systemUser);
            entityManager.persist(systemUserGroup);
            
            return systemUser;
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            Logger.getLogger(UserServiceBean.class.getName()).log(Level.SEVERE, null, ex);
            
            return null;
        }
    }
      
    //Attempt to login a user using their provided username and password
    public String login(String username, String password) {
        //Get the request of the current context and pass the input username and password to it for authentication
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        try {
            //This method will check in the realm to see if the detail match those in the database
            request.login(username, password);
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage("Login failed:" + e));
            return "error";
        }
        
        //If the user is a real user determine their role and send them to their associated home page
        if (request.isUserInRole("administrators")) {
            //Log the loggin event
            loggerBean.AddNewLog("Logged into the system");
            
            System.out.println("User logging in is an administrator");
            return "administratorHome";            
        } else if (request.isUserInRole("supervisors")) {
            loggerBean.AddNewLog("Logged into the system");
            
            System.out.println("User logging in is an supervisor");
            return "supervisorHome";       
        } else if (request.isUserInRole("students")) {
            loggerBean.AddNewLog("Logged into the system");
            
            System.out.println("User logging in is an student");
            return "studentHome";       
        } else {
            return "error";
        }
    }
    
    //Log the current user out of the context and return them to the index page
    public void logout() {
        //Get the current context
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            //Unlink the current users from the current session
            loggerBean.AddNewLog("Logged out of the system");
            
            //Log out
            request.logout();
            context.addMessage(null, new FacesMessage("User is logged out"));
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage("Logout failed."));
        }
    }        
    
    //Find a student entity using their sussexId/username
    public Student getStudentBySussexId(String sussexId) {
        try {
            return (Student) entityManager.createNamedQuery("findStudentWithSussexId").setParameter("sussexId", sussexId).getSingleResult();
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }
    
    //Find a supervisor entity using their sussexId/username
    public Supervisor getSupervisorBySussexId (String sussexId) {
        try {
            return (Supervisor) entityManager.createNamedQuery("getAllSupervisorsWithId").setParameter("sussexId", sussexId).getSingleResult();
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }
    
    //Find get the entity of the currently logged in student
    public Student getLoggedInStudent() {
        //Get the currently logged in student
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest(); 
        String currentStudentId = request.getUserPrincipal().getName();
        return getStudentBySussexId(currentStudentId);
    } 
    
    //Get the entity of the currently logged in supervisor
    public Supervisor getLoggedInSupervisor() {
        //Get the currently logged in supervisor
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest(); 
        String currentSupervisorId = request.getUserPrincipal().getName();
        return getSupervisorBySussexId(currentSupervisorId);
    }
    
    //Get all studetns on the system as list
    public List<Student> getAllStudents () {        
        try {
            return (List<Student>) entityManager.createNamedQuery("getAllStudents").getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
    //Get all supervisors in a list
    public List<Supervisor> getAllSupervisors () {
        try {
            return (List<Supervisor>) entityManager.createNamedQuery("getAllSupervisors").getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
    //Get all the administrators in a list
    public List<Supervisor> getAllAdministrators () {
        try {
            return (List<Supervisor>) entityManager.createNamedQuery("getAllSupervisors").getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
    //Determine the user group from the username of a user
    public String determineUsergroupFromUsername (String username) {
        return "";
    }
    
}
