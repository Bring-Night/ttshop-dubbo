package name.zhy.ttshop.file.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import name.zhy.ttshop.common.utils.FtpUtil;
import name.zhy.ttshop.common.utils.IDUtils;
import name.zhy.ttshop.file.service.FileService;

/**
 * Created by zhy on 2017/7/5 0005.
 */
@Service
public class FileServiceImpl implements FileService {

    @Value("${FTP_ADDRESS}")
    private String FTP_ADDRESS;
    @Value("${FTP_PORT}")
    private Integer FTP_PORT;
    @Value("${FTP_USERNAME}")
    private String FTP_USERNAME;
    @Value("${FTP_PASSWORD}")
    private String FTP_PASSWORD;
    @Value("${FTP_BASEPATH}")
    private String FTP_BASEPATH;

    @Override
    public Map<String, Object> uploadPicure(MultipartFile upfile) throws IOException {

        String originalFilename = upfile.getOriginalFilename();
        String type = originalFilename.substring(originalFilename.lastIndexOf("."));

        String fileName= IDUtils.genImageName()+type;
        //String basePath = "/home/ftpuser/www/images/";
        String filePath = new DateTime().toString("/yyyy/MM/dd");
        InputStream is = upfile.getInputStream();
        FtpUtil.uploadFile(FTP_ADDRESS,FTP_PORT,FTP_USERNAME,
                FTP_PASSWORD,FTP_BASEPATH,filePath,fileName,is);

        //设置返回值
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("state","SUCCESS");
        map.put("original",upfile.getOriginalFilename());
        map.put("size",upfile.getSize());
        map.put("url",filePath+"/"+fileName);
        map.put("title",fileName);
        map.put("type",type);
        return map;
    }
}
