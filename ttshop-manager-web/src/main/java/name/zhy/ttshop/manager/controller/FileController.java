package name.zhy.ttshop.manager.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import name.zhy.ttshop.file.service.FileService;


/**
 * Created by zhy on 2017/7/5 0005.
 */
@Controller
public class FileController {
    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/file/upload",method = RequestMethod.GET)
    public void config(HttpServletRequest request,
                       HttpServletResponse response) throws Exception{
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        response.setContentType("text/html");//火狐不报错

        String action = request.getParameter("action");
        if("config".equals(action)){
            PrintWriter out = response.getWriter();
            InputStream is = FileController.class.getClassLoader().getResourceAsStream("config.json");
            IOUtils.copy(is,out,"UTF-8");

        }

    }

    @RequestMapping(value = "/file/upload",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> upload(MultipartFile upfile) throws IOException {
        return fileService.uploadPicure(upfile);
    }
}
