package com.jag37.webapps2019.ejb;

import com.jag37.webapps2019.entity.Project;
import com.jag37.webapps2019.entity.ProjectTopic;
import com.jag37.webapps2019.entity.Student;
import com.jag37.webapps2019.entity.Supervisor;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Stateless
public class ProjectServiceBean {
    
    //Injected Beans
    
    @PersistenceContext
    EntityManager entityManager;
    
    @EJB
    UserServiceBean userServiceBean;
    
    @EJB
    ProjectTopicServiceBean projectTopicServiceBean;
    
    @EJB
    StringServiceBean stringServiceBean;
    
    @EJB
    LogServiceBean loggerBean;
    
    //Methods
    
    public ProjectServiceBean () {}
    
    //Register a new supervisors project into the system with the reuired fields passed to it from the JSF bean 
    public String registerSupervisorProject (String title, String description, String requiredSkills, String topicTitle) {
        //Get the topic instance based on the topic title
        ProjectTopic projectTopic = projectTopicServiceBean.getProjectTopicByTitle(topicTitle);        

        //Get the currently logged in supervisor
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest(); 
        String currentSupervisorId = request.getUserPrincipal().getName();
        Supervisor projectSupervisor = userServiceBean.getSupervisorBySussexId(currentSupervisorId);
        
        //Log the event
        loggerBean.AddNewLog(
                "Creating new Project: '" + title + 
                "' with Topic: '" + projectTopic.getTopicTitle() + 
                "' under Supervsor: '" + projectSupervisor.getSystemUserInstance().getUsername() + "'"); 
        
        //Create the new project from the variables gathered and update the associated entities
        Project newProject = new Project(title, description, requiredSkills, projectTopic, projectSupervisor);
        newProject.setStatus("Available");        
        projectSupervisor.addProject(newProject);
        
        //Persist the new project into the database
        entityManager.persist(newProject);
        
        return "registrationSuccess";
    }
    
    //Add a students proposed porject to the database
    public String proposeStudentProject (String title, String description, String requiredSkills, String topicTitle, String supervisorString) {
        //Get the topic instance based on the topic title
        ProjectTopic projectTopic = projectTopicServiceBean.getProjectTopicByTitle(topicTitle); 
        
        //Get the supervisor instance based on the supervisors ID
        Supervisor projectSupervisor = userServiceBean.getSupervisorBySussexId(stringServiceBean.extractIdFromString(supervisorString));

        //Get the currently logged in student       
        Student projectStudent = userServiceBean.getLoggedInStudent();
        
        //Log the event
        loggerBean.AddNewLog(
                "Proposing new Project: '" + title + 
                "' with Topic: '" + projectTopic.getTopicTitle() + 
                "' under Supervsor: '" + projectSupervisor.getSystemUserInstance().getUsername() + 
                "' as Student: '" + projectStudent.getSystemUserInstance().getUsername() + "'");
        
        //Create the project and update the associated entities
        Project newProject = new Project(title, description, requiredSkills, projectTopic, projectSupervisor);
        newProject.setStatus("Proposed");
        newProject.setProjectStudent(projectStudent);        
        projectStudent.setStudentsProject(newProject);
               
        //Store the project in the database
        entityManager.persist(newProject);        
        
        return "proposalSubmitted";
    }    
    
    //Get the current student logged in and assign them to the project they selected
    public String selectProjectAsStudent (String projectString) {
        //Find the project using its ID
        Long projectId = 0l;
        try {
            String projectIdAsString = stringServiceBean.extractIdFromString(projectString);

            projectId = Long.parseLong(projectIdAsString);
        } catch (NumberFormatException e) {}   
        
        //Link the chosen project to the student and vice versa, also update the project status
        Project chosenProject = (Project) entityManager.createNamedQuery("findProjectById").setParameter("targetId", projectId).getSingleResult();
        Student projectStudent = userServiceBean.getLoggedInStudent();     
        
        chosenProject.setProjectStudent(projectStudent);
        chosenProject.setStatus("Awaiting-Confirmation");
        
        loggerBean.AddNewLog("Selected a project");
        
        projectStudent.setStudentsProject(chosenProject);
        
        return "projectChosen";
    }
    
    //Get all the projects with the status 'Available'
    public List<Project> getListOfAllAvailableProjects() {
        return (List<Project>) entityManager.createNamedQuery("findAllProjectsWithStatus").setParameter("desiredStatus", "Available").getResultList();
    }   
    
    //Get all the projects with the status 'Approved'
    public List<Project> getAllApprovedProjects() {
        return (List<Project>) entityManager.createNamedQuery("findAllProjectsWithStatus").setParameter("desiredStatus", "Approved").getResultList();
    }
    
    //Get all the projects, both student and supervisor created that are currently waiting for approval by a supervisor
    public List<Project> getAllProjectsAwaitingApproval () {
        Supervisor currentSupervisor = userServiceBean.getLoggedInSupervisor();
        List<Project> projectsAwaiting = new ArrayList<>();
        
        //Search all projects and filter out one without the correct status
        for (Project pro : currentSupervisor.getProjects()) {
            if (pro.getStatus().equals("Proposed") || pro.getStatus().equals("Awaiting-Confirmation")) {
                projectsAwaiting.add(pro);
            }
        }
        
        //Return the list of awaiting projects
        return projectsAwaiting;
    }
    
    //Mark the project a student has selected as approved
    public String approveStudentProject (String stringOfProjectToApprove) {
        //Find the project from the provided ID
        Long projectId = 0l;
        try {
            String projectIdAsString = stringServiceBean.extractIdFromString(stringOfProjectToApprove);
            projectId = Long.parseLong(projectIdAsString);
        } catch (NumberFormatException e) {}   
        
        //Get the project instance and mark its status as 'Approved'
        Project projectToApprove = (Project) entityManager.createNamedQuery("findProjectById").setParameter("targetId", projectId).getSingleResult();
        projectToApprove.setStatus("Approved");
        
        loggerBean.AddNewLog("Approved Students Project");
        
        return "refreshApprovalsPage";
    }
    
    //Reject the students project selection
    public String rejectStudentProject (String stringOfProjectToReject) {
        //Find the project based on its ID
        Long projectId = 0l;
        try {
            String projectIdAsString = stringServiceBean.extractIdFromString(stringOfProjectToReject);
            projectId = Long.parseLong(projectIdAsString);
        } catch (NumberFormatException e) {}        
        
        //Get the project using its ID in a query
        Project projectToReject = (Project) entityManager.createNamedQuery("findProjectById").setParameter("targetId", projectId).getSingleResult();
        
        //Disassociate the project from the eneites its linked to
        projectToReject.getProjectStudent().setStudentsProject(null);
        projectToReject.setProjectStudent(null);
        projectToReject.getProjectSupervisor().getProjects().remove(projectToReject);
        
        loggerBean.AddNewLog("Reject Students Project");
        
        //Remove the project from the database
        entityManager.remove(projectToReject);
        
        return "refreshApprovalsPage";
    }    
    
    //Has a student already got a porject associated with them
    public String canStudentPickAProject () {
        Student currentStudent = userServiceBean.getLoggedInStudent();
        if (currentStudent.getStudentsProject() == null) {
            return "true";
        } else {
            return "false";
        }               
    }  
    
    //Unlink the selected student from their project and return the project to the 'Available' pool
    public String unselectProject (String stringOfProjectToUnselect) {        
        Project projectToUnselect = findProjectFromStringDesciption(stringOfProjectToUnselect);
        
        //Unlink the project from its associated entities
        projectToUnselect.getProjectStudent().setStudentsProject(null);
        projectToUnselect.setProjectStudent(null);
        projectToUnselect.setStatus("Available");
        
        loggerBean.AddNewLog("Unselected Students Project");
        
        return "unselectionSuccess";
    }    
    
    //Find a project in the database using a string description of it that contains its ID as the tail
    public Project findProjectFromStringDesciption (String stringDescription) {
        //Extract the ID of the project
        Long projectId = 0l;
        try {
            String projectIdAsString = stringServiceBean.extractIdFromString(stringDescription);
            projectId = Long.parseLong(projectIdAsString);
        } catch (NumberFormatException e) {}        
        
        //FInd the project using a JPQL query and the ID parameter
        Project projectFound = (Project) entityManager.createNamedQuery("findProjectById").setParameter("targetId", projectId).getSingleResult();
        return projectFound;
    }   
    
}
