package vn.ducmai.core.utils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import vn.ducmai.core.common.CoreConstant;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

public class FileUploadUtil {
    private final int maxMemorySize=1024*1024*3;//3MB
    private final int maxRequestSize=1024*1024*50;//50MB
    public Object[] writeOrUpdateFile(HttpServletRequest request, String path, Set<String> setTitle)  {
        //Check that we have a file upload request
        String location="/"+ CoreConstant.UPLOAD_FILE;
        String name=null;
        boolean check=true;
        String fileLocation=null;
        List<String> listFileName=new ArrayList<String>();
        List<String> listFileLocation=new ArrayList<String>();
        Map<String,String> mapReturnValue=new HashMap<String, String>();
        boolean isMultipart= ServletFileUpload.isMultipartContent(request);
        if(!isMultipart){
            System.out.println("have not enctype multipart/form-data");
        }
        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

// Set factory constraints
        factory.setSizeThreshold(maxMemorySize);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

// Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

// Set overall request size constraint
        upload.setSizeMax(maxRequestSize);

// Parse the request
        try {
            List<FileItem> items = upload.parseRequest(request);
            for (FileItem item:items) {
                if(!item.isFormField()){
                    name=item.getName();
                    if(StringUtils.isNotBlank(name)){
                        checkAnhCreateFolder(location,path);
                        File file=new File(location+File.separator+path+File.separator+name);
                        fileLocation=location+File.separator+path+File.separator+name;
                        listFileLocation.add(fileLocation);
                        if(StringUtils.isNotBlank(name)){
                            String fileName=path+File.separator+name;
                            listFileName.add(fileName);
                        }else {
                            listFileName.add("");
                        }
                        if(file.exists()){
                            file.delete();
                        }
                        item.write(file);
                    }
                }else {
                    String title=item.getFieldName();
                    String valueFiled=item.getString("UTF-8");
                    if(setTitle.contains(title)){
                        mapReturnValue.put(title,valueFiled);
                    }
                }
            }
        }catch (FileUploadException e){
            check=false;
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Object[]{check,listFileLocation,listFileName,mapReturnValue};
    }
    private void checkAnhCreateFolder(String adress, String path) {
        File folderRoot=new File(adress);
        if(!folderRoot.exists()){
            folderRoot.mkdirs();
        }
        File folderChild=new File(adress+File.separator+path);
        if(!folderChild.exists()){
            folderChild.mkdirs();
        }
    }
}
