package store.pengfeng.controller;

import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;
import store.pengfeng.request.UploadRequest;
import store.pengfeng.VO.UploadVO;
import store.pengfeng.common.response.Response;
import store.pengfeng.common.utils.ResponseGenerator;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * @author pengfeng
 * @date 2018/10/27 0:36
 */
@RestController
@RequestMapping("CRM")
public class AdminCrmController {

    @PostMapping("/upload/file")
    public Response<UploadVO> upload(@RequestBody UploadRequest request) throws Exception {
        String[] strings = common().upload_file(request.getLocal(), request.getType(), null);
        List<String> list = Arrays.asList(strings);
        UploadVO uploadVO = new UploadVO();
        String url = "http://47.100.206.217/" + list.get(0) + "/" + list.get(1);
        uploadVO.setUrl(url);
        return ResponseGenerator.success(uploadVO);
    }

    private static StorageClient common() throws IOException, MyException {
        String filePath = new ClassPathResource("fdfs_client.conf").getFile().getAbsolutePath();
        // 1、加载配置文件，配置文件中的内容就是 tracker 服务的地址。
        ClientGlobal.init(filePath);
        // 2、创建一个 TrackerClient 对象。直接 new 一个。
        TrackerClient trackerClient = new TrackerClient();
        // 3、使用 TrackerClient 对象创建连接，获得一个 TrackerServer 对象。
        TrackerServer trackerServer = trackerClient.getConnection();
        // 5、创建一个 StorageClient 对象，需要两个参数 TrackerServer 对象、StorageServer 的引用
        StorageServer storageServer = null;
        return new StorageClient(trackerServer, storageServer);
    }
    
}
