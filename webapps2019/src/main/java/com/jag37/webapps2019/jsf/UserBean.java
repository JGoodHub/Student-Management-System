package com.jag37.webapps2019.jsf;

import com.jag37.webapps2019.ejb.LogServiceBean;
import com.jag37.webapps2019.ejb.ProjectServiceBean;
import com.jag37.webapps2019.ejb.StringServiceBean;
import com.jag37.webapps2019.ejb.UserServiceBean;
import com.jag37.webapps2019.entity.Project;
import com.jag37.webapps2019.entity.Student;
import com.jag37.webapps2019.entity.Supervisor;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class UserBean {
    
    //Injected Beans
    
    @EJB
    ProjectServiceBean projectServiceBean;
    
    @EJB
    UserServiceBean userServiceBean;
    
    @EJB
    StringServiceBean stringServiceBean;
    
    @EJB
    LogServiceBean loggerBean;
    
    //Variables
    
    String stringOfProjectToApproveOrReject;
   
    String stringOfProjectToUnselect;
            
    //Methods
    
    //Get the number of projet to be accepted from the backend
    public String getSupervisorProjectNotificationCount () {
        return projectServiceBean.getAllProjectsAwaitingApproval().size() + "";
    }
    
    //Get all the projects awaiting to be accepted from the backend
    public List<Project> getAllProjectsAwaitingApproval () {
        return projectServiceBean.getAllProjectsAwaitingApproval();
    }
    
    //Get all the projects awaiting to be approved as strings from the backend
    public List<String> getAllProjectsAwaitingApprovalAsStrings () {
        return stringServiceBean.getAllProjectsAwaitingApprovalAsStrings();
    }
    
    //Get all the approved projects from the back end
    public List<Project> getAllApprovedProjects () {
        return projectServiceBean.getAllApprovedProjects();
    }
    
    //Get all the approved projects as strings from the back end
    public List<String> getAllApprovedProjectsAsStrings () {
        return stringServiceBean.getAllApprovedProjectsAsStrings();
    }
    
    //Approve the selected project in the back end
    public String approveProject () {
        return projectServiceBean.approveStudentProject(stringOfProjectToApproveOrReject);
    }
    
    //Reject the selected project in the back end
    public String rejectProject () {
        return projectServiceBean.rejectStudentProject(stringOfProjectToApproveOrReject);
    }
    
    //Determine if a student is allowed to pick a prokect or not
    public String canStudentPickAProject () {
        return projectServiceBean.canStudentPickAProject();
    }
    
    //Unlink the slected project for its student
    public String unselectProject () {
        return projectServiceBean.unselectProject(stringOfProjectToUnselect);
    }
    
    //Get all the students 
    public List<Student> getAllStudents () {
        return userServiceBean.getAllStudents();
    }
    
    //Get all students as string from the backend
    //public List<String> getAllStudentsAtStrings () {
        //return userServiceBean.getAllStudentsAtStrings();
    //}
    
    //Get all the supervisors from the back end
    public List<Supervisor> getAllSupervisors () {
        return userServiceBean.getAllSupervisors();
    }
    
    //Get all the supervisors as strings from the back end
    //public List<String> getAllSupervisorsAsStrings () {
        //return userServiceBean.getAllSupervisorsAsStrings();
    //}
        
    //Getters and Setters

    public String getStringOfProjectToApproveOrReject() {
        return stringOfProjectToApproveOrReject;
    }

    public void setStringOfProjectToApproveOrReject(String stringOfProjectToApproveOrReject) {
        this.stringOfProjectToApproveOrReject = stringOfProjectToApproveOrReject;
    }

    public String getStringOfProjectToUnselect() {
        return stringOfProjectToUnselect;
    }

    public void setStringOfProjectToUnselect(String stringOfProjectToUnselect) {
        this.stringOfProjectToUnselect = stringOfProjectToUnselect;
    }
    
    
    
    
    
}
