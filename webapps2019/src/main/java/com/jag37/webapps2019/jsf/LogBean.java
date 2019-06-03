package com.jag37.webapps2019.jsf;

import com.jag37.webapps2019.ejb.LogServiceBean;
import com.jag37.webapps2019.entity.Log;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class LogBean {
    
    //Injected Beans
    
    @EJB
    LogServiceBean logServiceBean;
    
    //Methods
    
    public LogBean () {}
    
    //Get all the logs from the back end
    public List<Log> getAllLogs () {
        return logServiceBean.getAllLogs();
    }   
    
    
}
