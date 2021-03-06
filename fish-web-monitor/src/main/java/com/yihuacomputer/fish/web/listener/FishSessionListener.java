/**
 * 
 */
package com.yihuacomputer.fish.web.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.api.session.ISessionManage;
import com.yihuacomputer.fish.session.SessionManage;
import com.yihuacomputer.fish.web.util.FishWebUtils;

/**
 * @author xuxigang
 *
 */
public class FishSessionListener implements HttpSessionListener, HttpSessionAttributeListener{
	
	@Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        // TODO Auto-generated method stub
        
    }

	@Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
        // TODO Auto-generated method stub
        
    }

	@Override
    public void attributeReplaced(HttpSessionBindingEvent se) {
        // TODO Auto-generated method stub
        
    }

	@Override
    public void sessionCreated(HttpSessionEvent se) {
        // TODO Auto-generated method stub
        
    }

	@Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        UserSession userSession = (UserSession) session.getAttribute(FishWebUtils.USER);
        if(userSession==null){
        	return ;
        }
        WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();  
        ISessionManage sessionManage=(ISessionManage)wac.getBean(SessionManage.class);
        session.removeAttribute(FishWebUtils.USER);
		sessionManage.logout(userSession.getUserCode());
    }

}
