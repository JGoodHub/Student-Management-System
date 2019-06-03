package com.jag37.webapps2019.ejb;

import com.jag37.webapps2019.entity.ProjectTopic;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ProjectTopicServiceBean {
    
    //Injected Beans
    
    @PersistenceContext
    EntityManager entityManager;
    
    @EJB
    LogServiceBean loggerBean;
    
    //Methods
    
    public ProjectTopicServiceBean () {}
    
    //Register a new topic in the database with a title and descirption
    public String registerProjectTopic (String topicTitle, String topicDescription) {
        try {
            //Create the instance and persist it into the database
            ProjectTopic newProjectTopic = new ProjectTopic(topicTitle, topicDescription);
            entityManager.persist(newProjectTopic);
        } catch (Exception e) {
            System.out.println("Error registering topic");
            System.out.println(e.toString());;
        }
        
        loggerBean.AddNewLog("Registered a new project topic");
        
        return "registrationSuccess";
    }
    
    //Get all projects topics that can be picked from
    public List<String> getAllProjectTopicTitles () {
        List<String> projectTopicTitles = new ArrayList<>();
        
        //Add just the topics to the returned array
        for (ProjectTopic topic : (List<ProjectTopic>) entityManager.createNamedQuery("getAllProjectTopics").getResultList()) {
            projectTopicTitles.add(topic.getTopicTitle());
        }
        
        return projectTopicTitles; 
    }
    
    //Find the instance of a project by using its title
    public ProjectTopic getProjectTopicByTitle (String title) {
        return (ProjectTopic) entityManager.createNamedQuery("findProjectTopicByTitle").setParameter("topicTitle", title).getResultList().get(0);
    }
        
}
