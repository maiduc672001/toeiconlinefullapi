package vn.ducmai.web.logic.controller.web;
import vn.ducmai.core.dto.ExerciseListenDTO;
import vn.ducmai.core.web.constant.WebConstant;
import vn.ducmai.core.web.utils.FormUtil;
import vn.ducmai.core.web.utils.RequestUtil;
import vn.ducmai.core.web.utils.SingletonService;
import vn.ducmai.web.logic.command.ExerciseListenCommand;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@WebServlet(urlPatterns = {"/exercise-listen.html"})
public class ExerciseListenController extends HttpServlet {
       private final String OPTION="option";
    private final String EXERCISE_LISTEN_ID="exerciseListenId";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ExerciseListenCommand command= FormUtil.populate(ExerciseListenCommand.class,req);
        if(command.getUrlType()!=null&&command.getUrlType().equals(WebConstant.LIST)){
            Map<String,Object> mapProperties=createMap(command);
            command.setMaxPageItems(5);
            RequestUtil.initManualSearchBean(command);
            Object[] objects= SingletonService.getExerciseListenService().findExerciseByProperties(mapProperties,null,null,command.getFirstItem(),command.getMaxPageItems());
            command.setTotalItems(Integer.parseInt(objects[0].toString()));
            command.setListResult((List<ExerciseListenDTO>) objects[1]);
            command.setTotalPages((int) Math.ceil((double) command.getTotalItems() / command.getMaxPageItems()));
            req.setAttribute(WebConstant.LIST_ITEMS,command);
            RequestDispatcher rd=req.getRequestDispatcher("views/web/exerciselisten/list.jsp");
            rd.forward(req,resp);
        }
        if(command.getUrlType()!=null&&command.getUrlType().equals(OPTION)){
            if(command.getExerciseListenId()!=null){
                req.setAttribute(WebConstant.ITEM,command);
            }
RequestDispatcher rd=req.getRequestDispatcher("views/web/exerciselisten/option.jsp");
rd.forward(req,resp);
        }
    }

    private Map<String, Object> createMap(ExerciseListenCommand command) {
        Map<String, Object> mapProperties=new HashMap<String, Object>();

        return mapProperties;
    }
}
