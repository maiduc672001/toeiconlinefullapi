package vn.ducmai.core.web.utils;

import vn.ducmai.core.web.constant.WebConstant;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class CreateMessage {
    public static Map<String,String> createMapMessage(){
        ResourceBundle bundle=ResourceBundle.getBundle("ResourcesBundle");
        Map<String,String> mapResponse=new HashMap<String, String>();
        mapResponse.put(WebConstant.SAVE,bundle.getString("label.save.success"));
        mapResponse.put(WebConstant.DELETE,bundle.getString("label.delete.success"));
        mapResponse.put(WebConstant.UPDATE,bundle.getString("label.update.success"));
        mapResponse.put(WebConstant.ERROR,bundle.getString("label.error"));
        return mapResponse;
    }
}
