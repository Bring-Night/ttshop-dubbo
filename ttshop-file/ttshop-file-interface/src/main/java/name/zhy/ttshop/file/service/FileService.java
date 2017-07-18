package name.zhy.ttshop.file.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * Created by zhy on 2017/7/5 0005.
 */
public interface FileService {
    Map<String,Object> uploadPicure(MultipartFile upfile) throws IOException;
}
