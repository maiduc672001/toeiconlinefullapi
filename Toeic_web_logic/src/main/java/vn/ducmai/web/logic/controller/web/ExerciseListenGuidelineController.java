package vn.ducmai.web.logic.controller.web;

import vn.ducmai.core.dto.ListenGuidelineDTO;
import vn.ducmai.core.web.constant.WebConstant;
import vn.ducmai.core.web.utils.FormUtil;
import vn.ducmai.core.web.utils.SingletonService;
import vn.ducmai.web.logic.command.ListenGuidelineCommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/exercise-listen.guideline.html"})
public class ExerciseListenGuidelineController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ListenGuidelineCommand command= FormUtil.populate(ListenGuidelineCommand.class,req);
        if(command.getExerciseListenId()!=null){
            ListenGuidelineDTO dto= SingletonService.getListenGuidelineServiceIns().findListenGuidelineByExciseId(command.getExerciseListenId());
            command.setPojo(dto);
        }
        req.setAttribute(WebConstant.ITEM,command);
        RequestDispatcher rd=req.getRequestDispatcher("views/web/exerciselisten/guideline.jsp");
        rd.forward(req,resp);
    }
}
