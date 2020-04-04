package vn.ducmai.web.logic.controller.admin;

import vn.ducmai.core.dto.ListenGuidelineDTO;
import vn.ducmai.core.web.constant.WebConstant;
import vn.ducmai.core.web.utils.FormUtil;
import vn.ducmai.core.web.utils.RequestUtil;
import vn.ducmai.core.web.utils.SingletonService;
import vn.ducmai.web.logic.command.ListenGuidelineCommand;

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

@WebServlet(urlPatterns = {"/admin-listenguideline-list.html","/admin-listenguideline-edit.html"})
public class ListenGuidelineController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ListenGuidelineCommand command= FormUtil.populate(ListenGuidelineCommand.class,req);
        RequestUtil.initSearchBean(req,command);
        Map<String,Object> properties=new HashMap<String, Object>();
        Object[] objects= SingletonService.getListenGuidelineServiceIns().findListenGuidelineByProperties(properties,command.getSortDirection(),command.getSortExpression(),command.getFirstItem(),command.getMaxPageItems());
        if(command.getUrlType()!=null&&command.getUrlType().equals(WebConstant.LIST)){
            command.setMaxPageItems(3);
            command.setTotalItems(Integer.parseInt(objects[0].toString()));
            List<ListenGuidelineDTO> dtos= (List<ListenGuidelineDTO>) objects[1];
            command.setListResult(dtos);
            req.setAttribute(WebConstant.LIST_ITEMS,command);
            RequestDispatcher rd=req.getRequestDispatcher("views/admin/listenguideline/list.jsp");
            rd.forward(req,resp);
        }
        if(command.getUrlType()!=null&&command.getUrlType().equals(WebConstant.EDIT)){
            RequestDispatcher rd=req.getRequestDispatcher("views/admin/listenguideline/edit.jsp");
            rd.forward(req,resp);
        }
    }
}
