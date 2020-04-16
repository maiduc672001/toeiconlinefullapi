package vn.ducmai.core.utils;

import javax.servlet.http.HttpServletRequest;

public class SessionUtil {
    private static SessionUtil sessionUtil=null;
    public static SessionUtil getInstance(){
if(sessionUtil==null){
    sessionUtil=new SessionUtil();
}
return sessionUtil;
    }
    public void setSession(HttpServletRequest request,String key,Object value){
        request.getSession().setAttribute(key,value);
    }
    public Object getSession(HttpServletRequest request,String key){
        return request.getSession().getAttribute(key);
    }
    public void deleteSession(HttpServletRequest request,String key){
        request.getSession().removeAttribute(key);
    }
}
