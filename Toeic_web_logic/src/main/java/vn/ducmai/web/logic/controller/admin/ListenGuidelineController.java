package vn.ducmai.web.logic.controller.admin;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import vn.ducmai.core.dto.ExerciseListenDTO;
import vn.ducmai.core.dto.ListenGuidelineDTO;
import vn.ducmai.core.utils.FileUploadUtil;
import vn.ducmai.core.web.constant.WebConstant;
import vn.ducmai.core.web.utils.*;
import vn.ducmai.web.logic.command.ListenGuidelineCommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns = {"/admin-listenguideline-list.html","/admin-listenguideline-edit.html","/ajax-listenguideline-edit.html"})
public class ListenGuidelineController extends HttpServlet {
    ResourceBundle bundle=ResourceBundle.getBundle("ResourcesBundle");
    /*@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ListenGuidelineCommand command= FormUtil.populate(ListenGuidelineCommand.class,req);
        RequestUtil.initSearchBean(req,command);
        Map<String,String> messageMap=createMessageMap();
        command.setMaxPageItems(3);
        Map<String,Object> properties=createRequestProperties(command);
        Object[] objects= SingletonService.getListenGuidelineServiceIns().findListenGuidelineByProperties(properties,command.getSortDirection(),command.getSortExpression(),command.getFirstItem(),command.getMaxPageItems());
        if((command.getUrlType()!=null&&command.getUrlType().equals(WebConstant.LIST))){
            if(command.getCrudaction()!=null){
                    MessageResponseUtil.getMessageResponse(req,command.getCrudaction(),messageMap);
            }

            command.setTotalItems(Integer.parseInt(objects[0].toString()));
            List<ListenGuidelineDTO> dtos= (List<ListenGuidelineDTO>) objects[1];
            command.setListResult(dtos);
            req.setAttribute(WebConstant.LIST_ITEMS,command);
            RequestDispatcher rd=req.getRequestDispatcher("views/admin/listenguideline/list.jsp");
            rd.forward(req,resp);
        }
        if(command.getUrlType()!=null&&command.getUrlType().equals(WebConstant.EDIT)) {
            if (command.getCrudaction() != null && command.getCrudaction().equals(WebConstant.DELETE)) {
                if (command.getCheckList() != null) {
                    String[] checks = command.getCheckList();
                    List<Integer> ids = new ArrayList<Integer>();
                    for (int i = 0; i < checks.length; i++) {
                        Integer id = Integer.valueOf(checks[i]);
                        ids.add(id);
                    }
                    Integer count = SingletonService.getListenGuidelineServiceIns().deleteListenGuideline(ids);
                    if (count == checks.length) {
                        MessageResponseUtil.getMessageResponse(req, command.getCrudaction(), messageMap);
                        resp.sendRedirect("/admin-listenguideline-list.html?urlType=list&&crudaction=delete");
                    } else {
                        String crudaction = WebConstant.REDIRECT_ERROR;
                        MessageResponseUtil.getMessageResponse(req, crudaction, messageMap);
                        resp.sendRedirect("/admin-listenguideline-list.html?urlType=list&&crudaction=error");
                    }
                }
            } else {
                ListenGuidelineDTO pojo = command.getPojo();
                if (pojo.getListenGuidelineId() != null) {
                    pojo = SingletonService.getListenGuidelineServiceIns().findListenGuidelineById(pojo.getListenGuidelineId());
                    command.setPojo(pojo);
                    req.setAttribute(WebConstant.ITEM, command);
                }
                RequestDispatcher rd = req.getRequestDispatcher("views/admin/listenguideline/edit.jsp");
                rd.forward(req, resp);
            }
        }
    }*/

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ListenGuidelineCommand command=FormUtil.populate(ListenGuidelineCommand.class,req);
        if(command.getUrlType()!=null&&command.getUrlType().equals(WebConstant.EDIT)){
            ListenGuidelineDTO dto=SingletonService.getListenGuidelineServiceIns().findListenGuidelineByExciseId(command.getExerciseListenId());
            command.setPojo(dto);
            req.setAttribute(WebConstant.ITEM,command);
            RequestDispatcher rd=req.getRequestDispatcher("views/admin/listenguideline/edit.jsp");
            rd.forward(req,resp);
        }
    }

    private Map<String, Object> createRequestProperties(ListenGuidelineCommand command) {
        Map<String,Object> mapProperties=new HashMap<String, Object>();
        String title=command.getPojo().getTitle();
        if(title!=null&&StringUtils.isNotBlank(title)) {
            mapProperties.put("title", command.getPojo().getTitle());
        }
        return mapProperties;
    }

   /* @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ListenGuidelineCommand command1=FormUtil.populate(ListenGuidelineCommand.class,req);
        ListenGuidelineCommand command=new ListenGuidelineCommand();
        FileUploadUtil fileUploadUtil=new FileUploadUtil();
        Set<String> setTitle=buideSetTitle();
        Object[] objects= fileUploadUtil.writeOrUpdateFile(req,WebConstant.LISTENGUIDELINE,setTitle);
        ListenGuidelineDTO pojo=command.getPojo();
        Map<String,String> mapValue= (Map<String, String>) objects[3];
        setValueListenGuideline(pojo, mapValue);
        String fileLocation= (String) objects[1];
        String fileName=(String) objects[2];
        boolean check=(Boolean) objects[0];
        if(check) {
            if (pojo.getListenGuidelineId() != null) {
                ListenGuidelineDTO dto=SingletonService.getListenGuidelineServiceIns().findListenGuidelineById(pojo.getListenGuidelineId());
                pojo.setCreatedDate(dto.getCreatedDate());
                if (StringUtils.isNotBlank(fileName)) {
                    pojo.setImage(fileName);
                }else {
                    pojo.setImage(dto.getImage());
                }

                pojo=SingletonService.getListenGuidelineServiceIns().updateListenGuideline(pojo);
                resp.sendRedirect("/admin-listenguideline-list.html?urlType=list&&crudaction=update");
            }else {
                pojo.setImage(objects[2].toString());
                SingletonService.getListenGuidelineServiceIns().saveListenGuideline(pojo);
                resp.sendRedirect("/admin-listenguideline-list.html?urlType=list&&crudaction=save");
            }
        }else {
            resp.sendRedirect("/admin-listenguideline-list.html?urlType=list&crudaction=error");
        }
    }*/

    private Map<String, String> createMessageMap() {
        Map<String,String> messageMap=new HashMap<String, String>();
        messageMap.put(WebConstant.UPDATE,bundle.getString("label.update.success"));
        messageMap.put(WebConstant.SAVE,bundle.getString("label.save.success"));
        messageMap.put(WebConstant.DELETE,bundle.getString("label.delete.success"));
        messageMap.put(WebConstant.ERROR,bundle.getString("label.error"));
return messageMap;
    }

    private void setValueListenGuideline(ListenGuidelineDTO pojo, Map<String, String> mapValue) {
        for (Map.Entry item:mapValue.entrySet()) {
if(item.getKey().equals("pojo.title")){
    pojo.setTitle(item.getValue().toString());
}
if(item.getKey().equals("pojo.content")){
    pojo.setContent(item.getValue().toString());
}
            if(item.getKey().equals("pojo.listenGuidelineId")){
                pojo.setListenGuidelineId(Integer.valueOf(item.getValue().toString()));
            }
        }
    }

    private Set<String> buideSetTitle() {
    Set<String> setTitle=new HashSet<String>();
    setTitle.add("pojo.title");
    setTitle.add("pojo.content");
        setTitle.add("pojo.listenGuidelineId");
    return setTitle;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FileUploadUtil fileUploadUtil=new FileUploadUtil();
        //Object[] objects= fileUploadUtil.writeOrUpdateFile(req,WebConstant.LISTENGUIDELINE,new HashSet<String>());
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        ObjectMapper objectMapper=new ObjectMapper();
        ListenGuidelineCommand command= HttpUtil.of(req.getReader()).toModel(ListenGuidelineCommand.class);
        ListenGuidelineDTO dto=new ListenGuidelineDTO();
        setValueByJson(command,dto);
        SingletonService.getListenGuidelineServiceIns().saveListenGuideline(dto);
        objectMapper.writeValue(resp.getOutputStream(),dto);

    }

    private void setValueByJson(ListenGuidelineCommand command, ListenGuidelineDTO dto) {
        if(command.getListenGuidelineId()!=null){
            dto.setListenGuidelineId(command.getListenGuidelineId());
        }
        ExerciseListenDTO dto1=SingletonService.getExerciseListenService().findExerciseListenUnique(command.getExerciseListenId());
        dto.setExerciseListenDTO(dto1);
        dto.setContent(command.getContent());
        dto.setTitle(command.getTitle());
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        ObjectMapper objectMapper=new ObjectMapper();
        ListenGuidelineDTO dto=new ListenGuidelineDTO();
        ListenGuidelineCommand command= HttpUtil.of(req.getReader()).toModel(ListenGuidelineCommand.class);
        setValueByJson(command,dto);
        SingletonService.getListenGuidelineServiceIns().updateListenGuideline(dto);
        objectMapper.writeValue(resp.getOutputStream(),dto);
    }
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        ObjectMapper objectMapper=new ObjectMapper();
        ListenGuidelineCommand command= HttpUtil.of(req.getReader()).toModel(ListenGuidelineCommand.class);
        List<Integer> ids=command.getIds();
        SingletonService.getListenGuidelineServiceIns().deleteListenGuideline(ids);
        objectMapper.writeValue(resp.getOutputStream(),ids);
    }
}
