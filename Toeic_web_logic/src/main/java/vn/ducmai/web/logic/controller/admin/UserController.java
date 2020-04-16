package vn.ducmai.web.logic.controller.admin;
import org.codehaus.jackson.map.ObjectMapper;
import vn.ducmai.core.dto.RoleDTO;
import vn.ducmai.core.dto.UserDTO;
import vn.ducmai.core.web.constant.WebConstant;
import vn.ducmai.core.web.utils.*;
import vn.ducmai.web.logic.command.UserCommand;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

@WebServlet(urlPatterns = {"/admin-user-list.html","/ajax-user-edit.html"})
public class UserController extends HttpServlet {
    ResourceBundle bundle=ResourceBundle.getBundle("ResourcesBundle");
    Map<String,String> mapResponse=buildMessageResponse();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserCommand command= FormUtil.populate(UserCommand.class,req);
        command.setMaxPageItems(2);
        RequestUtil.initSearchBean(req,command);
        if(command.getUrlType()!=null&&command.getUrlType().equals(WebConstant.LIST)) {
            Map<String, Object> mapProperties = createMapProperties(command);
            Object[] objects = SingletonService.getUserServiceIns().findUserByProperties(mapProperties, command.getSortDirection(), command.getSortExpression(), command.getFirstItem(), command.getMaxPageItems());
            command.setTotalItems(Integer.parseInt(objects[0].toString()));
            command.setListResult((List<UserDTO>) objects[1]);
            if(command.getCrudaction()!=null){

                MessageResponseUtil.getMessageResponse(req,command.getCrudaction(),mapResponse);
            }
            req.setAttribute(WebConstant.LIST_ITEMS, command);
            RequestDispatcher rd=req.getRequestDispatcher("/views/admin/user/list.jsp");
            rd.forward(req,resp);
        }
        if(command.getUrlType()!=null&&command.getUrlType().equals(WebConstant.EDIT)){
            UserDTO dto=command.getPojo();
            if(dto.getUserId()!=null){
                dto=SingletonService.getUserServiceIns().findUserById(dto.getUserId());
            }
            List<RoleDTO> roles=SingletonService.getRoleServiceIns().findAllRole();
            command.setPojo(dto);
            command.setRoles(roles);
            req.setAttribute(WebConstant.ITEM,command);
            RequestDispatcher rd=req.getRequestDispatcher("/views/admin/user/edit.jsp");
            rd.forward(req,resp);
        }
    }

    private Map<String, String> buildMessageResponse() {
        Map<String,String> mapResponse=new HashMap<String, String>();
        mapResponse.put(WebConstant.SAVE,bundle.getString("label.save.success"));
        mapResponse.put(WebConstant.DELETE,bundle.getString("label.delete.success"));
        mapResponse.put(WebConstant.UPDATE,bundle.getString("label.update.success"));
        mapResponse.put(WebConstant.ERROR,bundle.getString("label.error"));
        return mapResponse;
    }


    private Map<String, Object> createMapProperties(UserCommand command) {
        Map<String,Object> mapProperties=new HashMap<String, Object>();

        return mapProperties;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            req.setCharacterEncoding("UTF-8");
            resp.setContentType("application/json");
            ObjectMapper mapper = new ObjectMapper();
            UserCommand command = HttpUtil.of(req.getReader()).toModel(UserCommand.class);
            UserDTO dto = new UserDTO();
            setValueForUser(command, dto);
            SingletonService.getUserServiceIns().saveUser(dto);
            mapper.writeValue(resp.getOutputStream(), dto);


    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        ObjectMapper mapper=new ObjectMapper();
        UserCommand command= HttpUtil.of(req.getReader()).toModel(UserCommand.class);
        UserDTO dto=new UserDTO();
        setValueForUser(command,dto);
        SingletonService.getUserServiceIns().updateUser(dto);
        mapper.writeValue(resp.getOutputStream(),dto);
        /*try {

            resp.sendRedirect("/admin-user-list.html?urlType=list&&crudaciton=upload");
        }catch (Exception e){
            resp.sendRedirect("/admin-user-list.html?urlType=list&&crudaciton=error");
        }*/

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        ObjectMapper mapper=new ObjectMapper();
        UserCommand command=HttpUtil.of(req.getReader()).toModel(UserCommand.class);
        List<Integer> ids=command.getIds();
        SingletonService.getUserServiceIns().deleteUser(ids);
        mapper.writeValue(resp.getOutputStream(),ids);
    }

    private void setValueForUser(UserCommand command, UserDTO dto) {
        RoleDTO roleDTO=SingletonService.getRoleServiceIns().findRoleBy(command.getRoleId());
        dto.setRoleDTO(roleDTO);
        dto.setFullName(command.getFullName());
        dto.setName(command.getName());
        dto.setPassword(command.getPassword());
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        dto.setUserId(command.getUserId());
        dto.setCreatedDate(timestamp);
    }
}
