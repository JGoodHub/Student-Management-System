package com.jag37.webapps2019.jsf;

import com.jag37.webapps2019.ejb.LogServiceBean;
import com.jag37.webapps2019.ejb.ProjectServiceBean;
import com.jag37.webapps2019.ejb.StringServiceBean;
import com.jag37.webapps2019.ejb.UserServiceBean;
import com.jag37.webapps2019.entity.Project;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class ProjectBean {
    
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
        
    //Shared Project Creation
    String projectTitle;
    String projectDescription;
    String requiredSkills;
    String projectTopicTitle;
    
    //Student Proposal
    String supervisorString;
    
    //Project Selection
    String chosenProjectString;
       
    //Methods
    
    public ProjectBean () {}
    
    //Register a new supervisor project
    public String registerProjectAsSupervisor () {
        return projectServiceBean.registerSupervisorProject(projectTitle, projectDescription, requiredSkills, projectTopicTitle);
    }
    
    //Get all available projects in the database
    public List<Project> getListOfAllAvailableProjects () {
        return projectServiceBean.getListOfAllAvailableProjects();
    }
    
    //Get all the avaiables projects as strings
    public List<String> getListOfAllAvailableProjectsAsStrings () {
        return stringServiceBean.getAllAvailableProjectsAsStrings();
    }
    
    //get all the supervisors as strings
    public List<String> getListOfSupervisorsAsStrings () {
        return stringServiceBean.getAllSupervisorsAsStrings();
    }
       
    //Select a porject when logged in as a student
    public String selectProjectAsStudent () {
        return projectServiceBean.selectProjectAsStudent(chosenProjectString);
    }
    
    //propose a new project as a student
    public String proposeProjectAsStudent () {
        return projectServiceBean.proposeStudentProject(projectTitle, projectDescription, requiredSkills, projectTopicTitle, supervisorString);
    }
    
    //Get the currently logged in students project
    public String getStudentsCurrentProjectAsString () {
        return stringServiceBean.getStudentsCurrentProjectAsString();
    }
    
    //Getters and Setters

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(String requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public String getProjectTopicTitle() {
        return projectTopicTitle;
    }

    public void setProjectTopicTitle(String projectTopicTitle) {
        this.projectTopicTitle = projectTopicTitle;
    }

    public String getSupervisorString() {
        return supervisorString;
    }

    public void setSupervisorString(String supervisorString) {
        this.supervisorString = supervisorString;
    }

    public String getChosenProjectString() {
        return chosenProjectString;
    }

    public void setChosenProjectString(String chosenProjectString) {
        this.chosenProjectString = chosenProjectString;
    }    
    
}
