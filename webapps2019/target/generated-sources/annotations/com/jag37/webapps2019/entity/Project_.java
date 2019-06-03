package com.jag37.webapps2019.entity;

import com.jag37.webapps2019.entity.ProjectTopic;
import com.jag37.webapps2019.entity.Student;
import com.jag37.webapps2019.entity.Supervisor;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-11T15:47:46")
@StaticMetamodel(Project.class)
public class Project_ { 

    public static volatile SingularAttribute<Project, ProjectTopic> projectTopic;
    public static volatile SingularAttribute<Project, String> projectDescription;
    public static volatile SingularAttribute<Project, String> requiredSkills;
    public static volatile SingularAttribute<Project, Long> id;
    public static volatile SingularAttribute<Project, Student> projectStudent;
    public static volatile SingularAttribute<Project, Supervisor> projectSupervisor;
    public static volatile SingularAttribute<Project, String> projectTitle;
    public static volatile SingularAttribute<Project, String> status;

}