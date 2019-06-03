package com.jag37.webapps2019.entity;

import com.jag37.webapps2019.entity.Project;
import com.jag37.webapps2019.entity.SystemUser;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-11T15:47:46")
@StaticMetamodel(Student.class)
public class Student_ { 

    public static volatile SingularAttribute<Student, String> courseTitle;
    public static volatile SingularAttribute<Student, String> firstName;
    public static volatile SingularAttribute<Student, String> emailAddress;
    public static volatile SingularAttribute<Student, Project> studentsProject;
    public static volatile SingularAttribute<Student, SystemUser> systemUserInstance;
    public static volatile SingularAttribute<Student, String> surname;
    public static volatile SingularAttribute<Student, Long> id;

}