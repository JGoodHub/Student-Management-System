package com.jag37.webapps2019.entity;

import com.jag37.webapps2019.entity.Project;
import com.jag37.webapps2019.entity.SystemUser;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-11T15:47:46")
@StaticMetamodel(Supervisor.class)
public class Supervisor_ { 

    public static volatile SingularAttribute<Supervisor, String> firstName;
    public static volatile SingularAttribute<Supervisor, String> emailAddress;
    public static volatile SingularAttribute<Supervisor, String> telephoneNumber;
    public static volatile ListAttribute<Supervisor, Project> projects;
    public static volatile SingularAttribute<Supervisor, SystemUser> systemUserInstance;
    public static volatile SingularAttribute<Supervisor, String> surname;
    public static volatile SingularAttribute<Supervisor, Long> id;
    public static volatile SingularAttribute<Supervisor, String> department;

}