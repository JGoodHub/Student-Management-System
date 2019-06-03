package com.jag37.webapps2019.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import javax.validation.constraints.NotNull;

//Queries
@NamedQueries ({
    @NamedQuery(name="getAllProjectTopics", query="SELECT topic FROM ProjectTopic topic"),
    @NamedQuery(name="findProjectTopicByTitle", query="SELECT topic FROM ProjectTopic topic WHERE topic.topicTitle = :topicTitle")
})

@Entity
public class ProjectTopic implements Serializable {

    //Variables
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotNull @Column(unique = true)
    String topicTitle;
    
    @NotNull
    String topicDescription;
    
    //Methods
    
    public ProjectTopic () {}

    //Construct the project topic entity
    public ProjectTopic(String topicTitle, String topicDescription) {
        this.topicTitle = topicTitle;
        this.topicDescription = topicDescription;
    }

    //Getters and Setters
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.topicTitle);
        hash = 53 * hash + Objects.hashCode(this.topicDescription);
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
        final ProjectTopic other = (ProjectTopic) obj;
        if (!Objects.equals(this.topicTitle, other.topicTitle)) {
            return false;
        }
        if (!Objects.equals(this.topicDescription, other.topicDescription)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
}
