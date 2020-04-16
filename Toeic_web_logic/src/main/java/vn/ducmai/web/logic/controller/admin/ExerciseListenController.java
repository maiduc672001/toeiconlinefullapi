package vn.ducmai.web.logic.controller.admin;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.codehaus.jackson.map.ObjectMapper;
import vn.ducmai.core.dto.ExerciseListenDTO;
import vn.ducmai.core.web.constant.WebConstant;
import vn.ducmai.core.web.utils.*;
import vn.ducmai.web.logic.command.ExerciseListenCommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@WebServlet(urlPatterns = {"/admin-exercise-upload","/admin-exercise-listen-list.html","/admin-exercise-listen-edit.html"})
public class ExerciseListenController extends HttpServlet {
    private final String ADD_FILE_LISTEN="add_file_listen";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ExerciseListenCommand command= FormUtil.populate(ExerciseListenCommand.class,req);
        Map<String,Object> mapProperties=buildMapProperties(command);
        command.setMaxPageItems(3);
        Object[] objects= SingletonService.getExerciseListenService().findExerciseByProperties(mapProperties,command.getSortDirection(),command.getSortExpression(),0,20);
        List<ExerciseListenDTO> dtos= (List<ExerciseListenDTO>) objects[1];
        command.setListResult(dtos);
        if(command.getUrlType()!=null&&command.getUrlType().equals(WebConstant.LIST)){
            if(command.getCrudaction()!=null){
                MessageResponseUtil.getMessageResponse(req,command.getCrudaction(), CreateMessage.createMapMessage());
            }
            command.setTotalItems(Integer.parseInt(objects[0].toString()));
            req.setAttribute(WebConstant.LIST_ITEMS,command);
            RequestDispatcher rd=req.getRequestDispatcher("views/admin/exercise/list.jsp");
            rd.forward(req,resp);
        }
        if(command.getUrlType()!=null&&command.getUrlType().equals(WebConstant.EDIT)){
            RequestDispatcher rd=req.getRequestDispatcher("views/admin/exercise/detail.jsp");
            rd.forward(req,resp);
        }
    }

    private Map<String, Object> buildMapProperties(ExerciseListenCommand command) {
        Map<String,Object> map=new HashMap<String, Object>();
        if(StringUtils.isNotBlank(command.getPojo().getName())){
            map.put("name",command.getPojo().getName());
        }
        return map;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        ObjectMapper mapper=new ObjectMapper();
        ExerciseListenCommand command= HttpUtil.of(req.getReader()).toModel(ExerciseListenCommand.class);
        ExerciseListenDTO dto=new ExerciseListenDTO();
        setValueExe(dto,command);
        SingletonService.getExerciseListenService().saveExeListen(dto);
        mapper.writeValue(resp.getOutputStream(),dto);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        ObjectMapper mapper=new ObjectMapper();
        ExerciseListenCommand command=HttpUtil.of(req.getReader()).toModel(ExerciseListenCommand.class);
        List<Integer> ids=command.getIds();
        SingletonService.getExerciseListenService().deleteExerciseListen(ids);
        mapper.writeValue(resp.getOutputStream(),ids);
    }

    private void setValueExe(ExerciseListenDTO dto, ExerciseListenCommand command) {
        dto.setName(command.getName());
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        dto.setCreatedDate(timestamp);
        dto.setModifiedDate(timestamp);
    }
}
