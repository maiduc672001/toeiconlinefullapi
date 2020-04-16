package vn.ducmai.core.web.utils;
import vn.ducmai.core.web.constant.WebConstant;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class MessageResponseUtil {
    public static void getMessageResponse(HttpServletRequest request, String crudaction, Map<String,String> message){
        if(crudaction!=null&& crudaction.equals(WebConstant.UPDATE)){
            request.setAttribute(WebConstant.ALERT,WebConstant.SUCCESS);
            request.setAttribute(WebConstant.MESSAGE_RESPONSE, message.get(WebConstant.UPDATE));
        }
        if(crudaction!=null&& crudaction.equals(WebConstant.SAVE)){
            request.setAttribute(WebConstant.ALERT,WebConstant.SUCCESS);
            request.setAttribute(WebConstant.MESSAGE_RESPONSE, message.get(WebConstant.SAVE));
        }
        if(crudaction!=null&& crudaction.equals(WebConstant.DELETE)){
            request.setAttribute(WebConstant.ALERT,WebConstant.SUCCESS);
            request.setAttribute(WebConstant.MESSAGE_RESPONSE, message.get(WebConstant.DELETE));
        }
        if(crudaction!=null&& crudaction.equals(WebConstant.REDIRECT_ERROR)){
            request.setAttribute(WebConstant.ALERT,WebConstant.ERROR);
            request.setAttribute(WebConstant.MESSAGE_RESPONSE, message.get(WebConstant.REDIRECT_ERROR));
        }
    }

}
