package vn.ducmai.web.logic.controller.admin;
import vn.ducmai.core.dto.CheckLogin;
import vn.ducmai.core.dto.UserDTO;
import vn.ducmai.core.utils.SessionUtil;
import vn.ducmai.core.web.constant.WebConstant;
import vn.ducmai.core.web.utils.FormUtil;
import vn.ducmai.core.web.utils.SingletonService;
import vn.ducmai.web.logic.command.UserCommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

@WebServlet(urlPatterns = {"/login.html","/logout.html"})
public class LoginController extends HttpServlet {
    SessionUtil sessionUtil=new SessionUtil();
    ResourceBundle bundle=ResourceBundle.getBundle("ResourcesBundle");
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserCommand command=FormUtil.populate(UserCommand.class,req);
        String action=req.getParameter("action");
        if(action.equals("login")){
            RequestDispatcher rd=req.getRequestDispatcher("views/login.jsp");
            rd.forward(req,resp);
        }
        if(action.equals("logout")){
            sessionUtil.deleteSession(req,WebConstant.LOGIN_NAME);
            RequestDispatcher rd=req.getRequestDispatcher("views/web/home.jsp");
            rd.forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserCommand command= FormUtil.populate(UserCommand.class,req);
        UserDTO pojo=command.getPojo();
        CheckLogin checkLogin= SingletonService.getUserServiceIns().checklogin(pojo.getName(),pojo.getPassword());
        if(checkLogin.isCheck()){
            if(checkLogin.getRoleName().equals(WebConstant.ADMIN)){
                sessionUtil.setSession(req,WebConstant.LOGIN_NAME,checkLogin.getRoleName());
                req.setAttribute(WebConstant.ALERT,WebConstant.SUCCESS);
               resp.sendRedirect("/admin-home.html");
            }else if(checkLogin.getRoleName().equals(WebConstant.USER)){
                req.setAttribute(WebConstant.ALERT,WebConstant.SUCCESS);
                sessionUtil.setSession(req,WebConstant.LOGIN_NAME,checkLogin.getRoleName());
                resp.sendRedirect("/home.html");
            }
        }else {
            req.setAttribute(WebConstant.ALERT,WebConstant.ERROR);
            req.setAttribute(WebConstant.MESSAGE_RESPONSE,bundle.getString("label.checklogin"));
            RequestDispatcher rd=req.getRequestDispatcher("views/login.jsp");
            rd.forward(req,resp);
        }
    }
}
