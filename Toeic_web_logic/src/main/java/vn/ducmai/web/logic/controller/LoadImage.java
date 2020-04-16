package vn.ducmai.web.logic.controller;

import vn.ducmai.core.common.CoreConstant;
import vn.ducmai.core.web.constant.WebConstant;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/repository/*")
public class LoadImage extends HttpServlet {
    private final String imageBase="/"+ CoreConstant.UPLOAD_FILE;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String urlImage=req.getRequestURI();
        String relativeImagePath=urlImage.substring(("/repository/").length());
        ServletOutputStream outputStream=resp.getOutputStream();
        FileInputStream fin=new FileInputStream(imageBase+ File.separator+relativeImagePath);
        BufferedInputStream bin=new BufferedInputStream(fin);
        BufferedOutputStream bout=new BufferedOutputStream(outputStream);
        int ch=0;
        while ((ch=bin.read())!=-1)
            bout.write(ch);
            bin.close();
            fin.close();
            bout.close();
            outputStream.close();

    }
}
