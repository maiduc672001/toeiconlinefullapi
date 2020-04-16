package vn.ducmai.web.logic.controller.admin;
import java.util.*;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import vn.ducmai.core.dto.ExerciseListenDTO;
import vn.ducmai.core.dto.ExerciseListenQuestionDTO;
import vn.ducmai.core.utils.FileUploadUtil;
import vn.ducmai.core.utils.SessionUtil;
import vn.ducmai.core.web.constant.WebConstant;
import vn.ducmai.core.web.utils.FormUtil;
import vn.ducmai.core.web.utils.HttpUtil;
import vn.ducmai.core.web.utils.MessageResponseUtil;
import vn.ducmai.core.web.utils.SingletonService;
import vn.ducmai.web.logic.command.ExerciseListenQuestionCommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin-lisenquestion-edit.html","/admin-lisenquestion-list.html","/ajax-exercise-listen-question-edit.html"})
public class ExerciseListenQuestionController extends HttpServlet {
    ResourceBundle bundle=ResourceBundle.getBundle("ResourcesBundle");
    Map<String,String> mapResponse=buildMessageResponse();

    private Map<String, String> buildMessageResponse() {
        Map<String,String> mapResponse=new HashMap<String, String>();
        mapResponse.put(WebConstant.SAVE,bundle.getString("label.save.success"));
        mapResponse.put(WebConstant.DELETE,bundle.getString("label.delete.success"));
        mapResponse.put(WebConstant.UPDATE,bundle.getString("label.update.success"));
        mapResponse.put(WebConstant.ERROR,bundle.getString("label.error"));
        return mapResponse;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ExerciseListenQuestionCommand command= FormUtil.populate(ExerciseListenQuestionCommand.class,req);
        Map<String,Object> mapProperties=createMap(command);
        if(command.getUrlType()!=null&&command.getUrlType().equals(WebConstant.LIST)){
            if(SessionUtil.getInstance().getSession(req,"exerciseListenId")!=null){
                command.setExerciseListenId(Integer.valueOf(SessionUtil.getInstance().getSession(req,"exerciseListenId").toString()));
                SessionUtil.getInstance().deleteSession(req,"exerciseListenId");
            }
            if(command.getExerciseListenId()!=null){
                Object[] objects= SingletonService.getExerciseListenQuestionService().findExerciseQuestionServiceByProperties(mapProperties,command.getSortDirection(),command.getSortExpression(),null,null);
                command.setTotalItems(Integer.parseInt(objects[0].toString()));
                command.setListResult((List<ExerciseListenQuestionDTO>) objects[1]);
                if(command.getCrudaction()!=null){
                    MessageResponseUtil.getMessageResponse(req,command.getCrudaction(),mapResponse);
                }
                req.setAttribute(WebConstant.LIST_ITEMS,command);
                RequestDispatcher rd=req.getRequestDispatcher("views/admin/exerciselistenquestion/list.jsp");
                rd.forward(req,resp);
            }
        }if(command.getUrlType()!=null&&command.getUrlType().equals(WebConstant.EDIT)){
            if(command.getPojo().getExerciseListenQuestionId()!=null){
                ExerciseListenQuestionDTO dto=SingletonService.getExerciseListenQuestionService().findQuestionById(command.getPojo().getExerciseListenQuestionId());
                command.setPojo(dto);
            }
            req.setAttribute(WebConstant.ITEM,command);
            RequestDispatcher rd=req.getRequestDispatcher("views/admin/exerciselistenquestion/detail.jsp");
            rd.forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FileUploadUtil fileUploadUtil=new FileUploadUtil();
        ExerciseListenQuestionCommand command=new ExerciseListenQuestionCommand();
        Set<String> set=createSetListen();
        Object[] objects= fileUploadUtil.writeOrUpdateFile(req,WebConstant.EXERCISE_LISTEN,set);
        boolean check=(Boolean) objects[0];
        Map<String, String> mapValue = (Map<String, String>) objects[3];
        SessionUtil.getInstance().setSession(req,"exerciseListenId",mapValue.get("exerciseListenId"));
        try {
            if(check) {
                List<String> listFileName = (List<String>) objects[2];
                String fileImage = listFileName.get(0);
                String fileAudio = listFileName.get(1);
                ExerciseListenQuestionDTO dto=new ExerciseListenQuestionDTO();
                setValueFromRequest(dto, mapValue);
                if(dto.getExerciseListenQuestionId()!=null){
                    ExerciseListenQuestionDTO dto1=SingletonService.getExerciseListenQuestionService().findQuestionById(dto.getExerciseListenQuestionId());
                    if(fileAudio!=null){
                        dto.setAudio(fileAudio);
                    }else {
                        dto.setAudio(dto1.getAudio());
                    }
                    if(fileImage!=null){
                        dto.setImage(fileImage);
                    }else {
                        dto.setImage(dto1.getImage());
                    }
                    SingletonService.getExerciseListenQuestionService().updateExerciseListenQuestion(dto);

                    resp.sendRedirect("/admin-lisenquestion-list.html?urlType=list&&crudaction=update");
                }else{
                    dto.setImage(fileImage);
                    dto.setAudio(fileAudio);
                    SingletonService.getExerciseListenQuestionService().saveExerciseListenQuestion(dto);
                    resp.sendRedirect("/admin-lisenquestion-list.html?urlType=list&&crudaction=save");
                }
            }
        }catch (Exception e){
            resp.sendRedirect("/admin-lisenquestion-list.html?urlType=list&&crudaction=error");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        ObjectMapper mapper=new ObjectMapper();
        ExerciseListenQuestionCommand command= HttpUtil.of(req.getReader()).toModel(ExerciseListenQuestionCommand.class);
        SingletonService.getExerciseListenQuestionService().deleteExerciseListenQuestion(command.getIds());
        mapper.writeValue(resp.getOutputStream(),command.getIds());
    }

    private void setValueFromRequest(ExerciseListenQuestionDTO dto, Map<String, String> mapValue) {
        dto.setCorrectAnswer(mapValue.get("correctAnswer"));
        dto.setOption1(mapValue.get("option1"));
        dto.setOption2(mapValue.get("option2"));
        dto.setOption3(mapValue.get("option3"));
        dto.setOption4(mapValue.get("option4"));
        dto.setQuestion(mapValue.get("question"));
        ExerciseListenDTO dto1=SingletonService.getExerciseListenService().findExerciseListenUnique(Integer.valueOf(mapValue.get("exerciseListenId")));
        dto.setExerciseListenDTO(dto1);
        if(mapValue.get("exerciseListenQuestionId")!=null) {
            dto.setExerciseListenQuestionId(Integer.valueOf(mapValue.get("exerciseListenQuestionId")));
        }
    }

    private Set<String> createSetListen() {
        Set<String> set=new HashSet<String>();
        set.add("question");
        set.add("option1");
        set.add("option2");
        set.add("option3");
        set.add("option4");
        set.add("correctAnswer");
        set.add("exerciseListenId");
        set.add("exerciseListenQuestionId");
        return set;
    }

    private Map<String, Object> createMap(ExerciseListenQuestionCommand command) {
        Map<String, Object> mapProperties = new HashMap<String, Object>();
        if (command.getExerciseListenId() != null) {
            mapProperties.put("exerciseListenId", command.getExerciseListenId().toString());
        }
        if(StringUtils.isNotBlank(command.getPojo().getQuestion())){
            mapProperties.put("question",command.getPojo().getQuestion());
        }
        return mapProperties;
    }
}
