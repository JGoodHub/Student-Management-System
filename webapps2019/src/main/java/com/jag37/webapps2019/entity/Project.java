package com.jag37.webapps2019.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

//Queries
@NamedQueries ({
    @NamedQuery(name="findAllProjects", query="SELECT p FROM Project p"),
    @NamedQuery(name="findAllProjectsWithStatus", query="SELECT p FROM Project p WHERE p.status = :desiredStatus"),
    @NamedQuery(name="findProjectById", query="SELECT p FROM Project p WHERE p.id = :targetId"),
    @NamedQuery(name="findAllProjectsWithSupervisorId", query="SELECT p FROM Project p WHERE p.projectSupervisor.systemUserInstance.username = :sussexId")
})

@Entity
public class Project implements Serializable {

    //Variables
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotNull
    String projectTitle;
    
    @NotNull
    String projectDescription;
    
    @NotNull
    String requiredSkills;
    
    @NotNull
    String status;
    
    @NotNull @OneToOne
    ProjectTopic projectTopic;
    
    @NotNull @ManyToOne
    Supervisor projectSupervisor;
    
    @OneToOne
    Student projectStudent;

    //Methods
    
    public Project () {}

    //Construct the project entity
    public Project(String projectTitle, String projectDescription, String requiredSkills, ProjectTopic projectTopic, Supervisor projectSupervisor) {
        this.projectTitle = projectTitle;
        this.projectDescription = projectDescription;
        this.requiredSkills = requiredSkills;
        this.projectTopic = projectTopic;
        this.projectSupervisor = projectSupervisor;
    }    
    
    //Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ProjectTopic getProjectTopic() {
        return projectTopic;
    }

    public void setProjectTopic(ProjectTopic projectTopic) {
        this.projectTopic = projectTopic;
    }

    public Supervisor getProjectSupervisor() {
        return projectSupervisor;
    }

    public void setProjectSupervisor(Supervisor projectSupervisor) {
        this.projectSupervisor = projectSupervisor;
    }

    public Student getProjectStudent() {
        return projectStudent;
    }

    public void setProjectStudent(Student projectStudent) {
        this.projectStudent = projectStudent;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.id);
        hash = 17 * hash + Objects.hashCode(this.projectTitle);
        hash = 17 * hash + Objects.hashCode(this.projectDescription);
        hash = 17 * hash + Objects.hashCode(this.requiredSkills);
        hash = 17 * hash + Objects.hashCode(this.status);
        hash = 17 * hash + Objects.hashCode(this.projectTopic);
        hash = 17 * hash + Objects.hashCode(this.projectSupervisor);
        hash = 17 * hash + Objects.hashCode(this.projectStudent);
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
        final Project other = (Project) obj;
        if (!Objects.equals(this.projectTitle, other.projectTitle)) {
            return false;
        }
        if (!Objects.equals(this.projectDescription, other.projectDescription)) {
            return false;
        }
        if (!Objects.equals(this.requiredSkills, other.requiredSkills)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.projectTopic, other.projectTopic)) {
            return false;
        }
        if (!Objects.equals(this.projectSupervisor, other.projectSupervisor)) {
            return false;
        }
        if (!Objects.equals(this.projectStudent, other.projectStudent)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Project{" + "id=" + id + ", projectTitle=" + projectTitle + ", status=" + status + ", projectTopic=" + projectTopic + ", projectSupervisor=" + projectSupervisor + ", projectStudent=" + projectStudent + '}';
    }
    
    
    
}
