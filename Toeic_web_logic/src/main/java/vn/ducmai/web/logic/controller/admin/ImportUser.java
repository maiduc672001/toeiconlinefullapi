package vn.ducmai.web.logic.controller.admin;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import vn.ducmai.core.dto.UserImportDTO;
import vn.ducmai.core.utils.ExcelRead;
import vn.ducmai.core.utils.FileUploadUtil;
import vn.ducmai.core.utils.SessionUtil;
import vn.ducmai.core.web.constant.WebConstant;
import vn.ducmai.core.web.utils.FormUtil;
import vn.ducmai.core.web.utils.MessageResponseUtil;
import vn.ducmai.core.web.utils.RequestUtil;
import vn.ducmai.core.web.utils.SingletonService;
import vn.ducmai.web.logic.command.UserCommand;
import vn.ducmai.web.logic.command.UserImportCommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns = {"/admin-import-user.html","/admin-import-validate-user.html"})
public class ImportUser extends HttpServlet {
    private final String LIST_USER_IMPORT="list_import";
    private final String VALIDATE_IMPORT="validate_import";
    ResourceBundle bundle=ResourceBundle.getBundle("ResourcesBundle");
    Map<String,String> mapResponse=buildMessageResponse();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserCommand command= FormUtil.populate(UserCommand.class,req);
        command.setMaxPageItems(20);
        RequestUtil.initSearchBean(req,command);
        if(command.getUrlType()!=null&&command.getUrlType().equals(WebConstant.IMPORT_USER)){
            RequestDispatcher rd=req.getRequestDispatcher("views/admin/user/import.jsp");
            rd.forward(req,resp);
        }
        if(command.getUrlType()!=null&&command.getUrlType().equals(VALIDATE_IMPORT)){
            List<UserImportDTO> userImportDTOS=(List<UserImportDTO>) SessionUtil.getInstance().getSession(req,LIST_USER_IMPORT);
            command.setUserImportDTOS(userImportDTOS);
            command.setTotalItems(userImportDTOS.size());
            req.setAttribute(WebConstant.LIST_ITEMS,command);
            RequestDispatcher rd=req.getRequestDispatcher("views/admin/user/import.jsp");
            rd.forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            UserImportCommand userCommand = FormUtil.populate(UserImportCommand.class, req);
            FileUploadUtil fileUploadUtil = new FileUploadUtil();
            Set<String> stringSet = buildSetString();
            Object[] objects = fileUploadUtil.writeOrUpdateFile(req, WebConstant.USER_FILE, stringSet);
            boolean check = (Boolean) objects[0];
            if(check) {
                Map<String, String> mapValue = (Map<String, String>) objects[3];
                setUrlType(userCommand, mapValue);
            }
            if(userCommand.getUrlType()!=null&&userCommand.getUrlType().equals(WebConstant.IMPORT_USER)) {
                if (check) {
                    List<String> listFileName= (List<String>) objects[2];
                    List<String> listFileLocation= (List<String>) objects[1];
                    String fileName = listFileName.get(0);
                    String fileLocation = listFileLocation.get(0);
                    List<UserImportDTO> userImportDTOSList = getUserFromExcel(fileLocation, fileName);
                    validateUserImport(userImportDTOSList);
                    req.setAttribute(WebConstant.LIST_ITEMS, userCommand);
                    SessionUtil.getInstance().setSession(req, LIST_USER_IMPORT, userImportDTOSList);
                    resp.sendRedirect("/admin-import-validate-user.html?urlType=validate_import");
                } else {
                    MessageResponseUtil.getMessageResponse(req, "error", mapResponse);
                    RequestDispatcher rd = req.getRequestDispatcher("views/admin/user/list.jsp");
                    rd.forward(req, resp);
                }
            }
            if(userCommand.getUrlType()!=null&&userCommand.getUrlType().equals(WebConstant.SAVE_USER)){
                List<UserImportDTO> userImportDTOS= (List<UserImportDTO>) SessionUtil.getInstance().getSession(req,LIST_USER_IMPORT);
                SingletonService.getUserServiceIns().saveUserImport(userImportDTOS);
                SessionUtil.getInstance().deleteSession(req,LIST_USER_IMPORT);
                resp.sendRedirect("/admin-user-list.html?urlType=list");
            }
        }catch (Exception e){
            req.setAttribute(WebConstant.MESSAGE_RESPONSE,WebConstant.REDIRECT_ERROR);

        }

    }

    private void setUrlType(UserImportCommand userCommand, Map<String, String> mapValue) {
        userCommand.setUrlType(mapValue.get("urlType"));
    }

    private void validateUserImport(List<UserImportDTO> userImportDTOSList) {
        Set<String> setName=new HashSet<String>();
        for (UserImportDTO dto:userImportDTOSList) {
            validateBlank(dto);
            validateTrung(dto,setName);
        }
    }

    private void validateTrung(UserImportDTO dto,Set<String> setName) {
        String message=dto.getMessage();
        boolean check=false;
        if(!setName.contains(dto.getUserName())){
            setName.add(dto.getUserName());
        }else {
            message+="<br/>";
            message+="Tên đăng nhập đã tồn tại";
        }
        if(StringUtils.isBlank(message)){
            check=true;
        }else{
            message=message.substring(5);
        }
        dto.setMessage(message);
        dto.setCheck(check);
    }
    private Set<String> buildSetString() {
        Set<String> set=new HashSet<String>();
        set.add("urlType");
        return set;
    }
    private void validateBlank(UserImportDTO dto) {
        String message="";
        boolean check=false;
        if(StringUtils.isBlank(dto.getUserName())){
            message+="<br/>";
            message+="Tên trống";
        }
        if(StringUtils.isBlank(dto.getFullName())){
            message+="<br/>";
            message+="Tên đầy đủ trống";
        }
        if(StringUtils.isBlank(dto.getPassword())){
            message+="<br/>";
            message+="Mật khẩu trống";
        }
        if(StringUtils.isBlank(dto.getRoleName())){
            message="<br/>";
            message+="Tên người dùng trống";
        }
        if(StringUtils.isBlank(message)){
            check=true;
        }
        dto.setCheck(check);
        dto.setMessage(message);
    }

    private List<UserImportDTO> getUserFromExcel(String fileLocation, String fileName) {
        List<UserImportDTO> userDTOList=new ArrayList<UserImportDTO>();
        try {
            Workbook workbook= ExcelRead.getWorkBook(fileName,fileLocation);
            Sheet sheet=workbook.getSheetAt(0);
            for(int i=0;i<sheet.getLastRowNum();i++){
                Row row=sheet.getRow(i);
                UserImportDTO dto=readDataFromExcel(row);
                userDTOList.add(dto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userDTOList;
    }

    private UserImportDTO readDataFromExcel(Row row) {
        UserImportDTO dto=new UserImportDTO();
        dto.setUserName(ExcelRead.getCellValue(row.getCell(0)));
        dto.setFullName(ExcelRead.getCellValue(row.getCell(2)));
        dto.setPassword(ExcelRead.getCellValue(row.getCell(1)));
        dto.setRoleName(ExcelRead.getCellValue(row.getCell(3)));
        return dto;
    }
    private Map<String, String> buildMessageResponse() {
        Map<String,String> mapResponse=new HashMap<String, String>();
        mapResponse.put(WebConstant.SAVE,bundle.getString("label.save.success"));
        mapResponse.put(WebConstant.DELETE,bundle.getString("label.delete.success"));
        mapResponse.put(WebConstant.UPDATE,bundle.getString("label.update.success"));
        mapResponse.put(WebConstant.ERROR,bundle.getString("label.error"));
        return mapResponse;
    }
}
