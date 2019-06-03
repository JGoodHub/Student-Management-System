package com.jag37.webapps2019.jsf;

import com.jag37.webapps2019.ejb.ProjectTopicServiceBean;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class ProjectTopicBean {
    
    //Injected Beans
    
    @EJB
    ProjectTopicServiceBean projectTopicService;
    
    //Variables
    
    String topicTitle;
    String topicDescription;
    
    //Methods
    
    public ProjectTopicBean () {}
    
    //Register a new project top in the back end
    public String registerProjectTopic () {
        return projectTopicService.registerProjectTopic(topicTitle, topicDescription);
    }
    
    //Get all the topic titles from the back end
    public List<String> getProjectTopicTitles () {      
        return projectTopicService.getAllProjectTopicTitles(); 
    }
    
    //Getters and Setters

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public String getTopicDescription() {
        return topicDescription;
    }

    public void setTopicDescription(String topicDescription) {
        this.topicDescription = topicDescription;
    }
    
}
