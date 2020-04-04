package vn.ducmai.core.web.utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;

public class FormUtil {
    public static <T> T populate(Class<T> tClass, HttpServletRequest request){
        T object=null;
        try {
            object=(T) tClass.newInstance();
            BeanUtils.populate(object,request.getParameterMap());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return object;
    }
}
