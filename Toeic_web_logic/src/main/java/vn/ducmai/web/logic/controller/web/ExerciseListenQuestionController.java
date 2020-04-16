package vn.ducmai.web.logic.controller.web;
import vn.ducmai.core.dto.ExerciseListenQuestionDTO;
import vn.ducmai.core.web.constant.WebConstant;
import vn.ducmai.core.web.utils.FormUtil;
import vn.ducmai.core.web.utils.RequestUtil;
import vn.ducmai.core.web.utils.SingletonService;
import vn.ducmai.web.logic.command.ExerciseListenQuestionCommand;

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
@WebServlet(urlPatterns = {"/exercise-listen.question.html","/ajax-exercise-listen-question.html"})
public class ExerciseListenQuestionController extends HttpServlet {
    //private final String LISTEN_QUESTION_EXE="question";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ExerciseListenQuestionCommand command= FormUtil.populate(ExerciseListenQuestionCommand.class,req);
    if(command.getExerciseListenId()!=null){
        Map<String,Object> mapProperties=createMap(command);
        command.setMaxPageItems(1);
        RequestUtil.initManualSearchBean(command);
        Object[] objects= SingletonService.getExerciseListenQuestionService().findExerciseQuestionServiceByProperties(mapProperties,command.getSortDirection(),command.getSortExpression(),command.getFirstItem(),command.getMaxPageItems());
        command.setTotalItems(Integer.parseInt(objects[0].toString()));
        command.setListResult((List<ExerciseListenQuestionDTO>) objects[1]);
        command.setTotalPages((int) Math.ceil((double)command.getTotalItems()/command.getMaxPageItems()));
    req.setAttribute(WebConstant.LIST_ITEMS,command);
    RequestDispatcher rd=req.getRequestDispatcher("views/web/exerciselisten/question.jsp");
    rd.forward(req,resp);
}
    }

    private Map<String, Object> createMap(ExerciseListenQuestionCommand command) {
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("exerciseListenId",command.getExerciseListenId().toString());
        return map;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
ExerciseListenQuestionCommand command=FormUtil.populate(ExerciseListenQuestionCommand.class,req);
if(command.getExerciseListenQuestionId()!=null){
    ExerciseListenQuestionDTO dto=SingletonService.getExerciseListenQuestionService().findQuestionById(command.getExerciseListenQuestionId());
    if(command.getAnswerUser()!=null&&command.getAnswerUser().equals(dto.getCorrectAnswer())){
        command.setCheck(true);
    }else {
        command.setCheck(false);
    }
    req.setAttribute(WebConstant.ITEM,command);
    RequestDispatcher rd=req.getRequestDispatcher("views/web/exerciselisten/result.jsp");
    rd.forward(req,resp);
}

    }
}
