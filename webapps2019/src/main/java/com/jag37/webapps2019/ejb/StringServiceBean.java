package com.jag37.webapps2019.ejb;

import com.jag37.webapps2019.entity.Project;
import com.jag37.webapps2019.entity.Student;
import com.jag37.webapps2019.entity.Supervisor;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class StringServiceBean {
    
    //Injected Beans
    
    @EJB
    ProjectServiceBean projectServiceBean;
    
    @EJB
    UserServiceBean userServiceBean;
    
    @EJB
    LogServiceBean loggerBean;
    
    //Methods
    
    public StringServiceBean () {}    
    
    //Get a string description of the current logged in student prokject, this can be in any state
    public String getStudentsCurrentProjectAsString() {
        //Get the current logged in student and their project, is they have one
        Student currentStudent = userServiceBean.getLoggedInStudent();        
        Project chosenProject = currentStudent.getStudentsProject();
        
        //If the project isn't null return its title and status
        if (chosenProject != null) {        
            return  "Title: " + chosenProject.getProjectTitle() + " | " +
                    "Description: " + chosenProject.getProjectDescription() + " | " +
                    "Topic: " + chosenProject.getProjectTopic().getTopicTitle() + " | " + 
                    "Supervisor: " + chosenProject.getProjectSupervisor().getFullName() + " | " +
                    "Project Status: " + chosenProject.getStatus();
        } else {
            //If no project is associated just return as such
            System.out.println("Project null so no project selected");
            return "No project selected";
        }
    }
    
    //Get string descriptions of all projects waiting to be approved by supervisors
    public List<String> getAllProjectsAwaitingApprovalAsStrings () {
        List<String> projectsAwaitingStings = new ArrayList<>();
        
        //Convert the projects into descriptions and return them in an array, appending the ID to the end for later identifcation
        for (Project pro : projectServiceBean.getAllProjectsAwaitingApproval()) {
            projectsAwaitingStings.add(
                    pro.getProjectTitle() + " selected by student " + 
                    pro.getProjectStudent().getSystemUserInstance().getUsername() + 
                    " - ID:" + pro.getId()
            );
        }
        
        return projectsAwaitingStings;
    }    
    
    //Get all 'Available' projects as a string description
    public List<String> getAllAvailableProjectsAsStrings () {
        List<String> dropdownProjectStrings = new ArrayList<>();
        List<Project> availableProjects = projectServiceBean.getListOfAllAvailableProjects();
        
        //Convert each project into a description and add it ot the returned array
        for (Project project : availableProjects) {
            String item = "";
            
            item = project.getProjectTitle() + "(" + project.getProjectTopic().getTopicTitle() + ") by " + 
                   project.getProjectSupervisor().getFullName() + "(" + project.getProjectSupervisor().getSystemUserInstance().getUsername() + ") - ID:" +
                   project.getId();
            
            dropdownProjectStrings.add(item);
        }
        
        return dropdownProjectStrings;
    } 
    
    //Get all 'Approved' proejcts and return them as string descriptions
    public List<String> getAllApprovedProjectsAsStrings() {
        List<String> projectStrings = new ArrayList<>();
        
        //Convert each project into a description and add it ot the returned array
        for (Project project : projectServiceBean.getAllApprovedProjects()) {
            String item = "";
            
            item = project.getProjectTitle() + "(" + project.getProjectTopic().getTopicTitle() + ") by " + 
                   project.getProjectSupervisor().getFullName() + "(" + project.getProjectSupervisor().getSystemUserInstance().getUsername() + ") - ID:" +
                   project.getId();
            
            projectStrings.add(item);
        }
        
        return projectStrings;
    }  
    
    //Get all the supervisors as string descriptions
    public List<String> getAllSupervisorsAsStrings () {
        List<String> listOfSupervisorStrings = new ArrayList<>();  
        
        //Convert each supervisor into a description and add it ot the returned array
        for (Supervisor supervisor : userServiceBean.getAllSupervisors()) {
            listOfSupervisorStrings.add(supervisor.getFullName() + " - ID:" + supervisor.getSystemUserInstance().getUsername());        
        }
        
        return listOfSupervisorStrings;
    }
    
    //Extract the ID from the tail end of a string descriptor
    public String extractIdFromString (String descriptorString) {
        String id = descriptorString.substring(descriptorString.indexOf("ID:") + 3);
        return id;
    }
    
    
}
