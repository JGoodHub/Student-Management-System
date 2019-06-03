package com.jag37.webapps2019.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-11T15:47:46")
@StaticMetamodel(Log.class)
public class Log_ { 

    public static volatile SingularAttribute<Log, String> timeStamp;
    public static volatile SingularAttribute<Log, String> userClass;
    public static volatile SingularAttribute<Log, Long> id;
    public static volatile SingularAttribute<Log, String> message;
    public static volatile SingularAttribute<Log, String> username;

}