package store.pengfeng.Service.impl;

import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import store.pengfeng.Service.UploadService;
import store.pengfeng.VO.UploadVO;
import store.pengfeng.common.response.Result;
import store.pengfeng.common.utils.ResultGenerator;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static store.pengfeng.common.Constant.CONFIGFEIL;
import static store.pengfeng.common.Constant.SERVICEHOST;

@Service
public class UploadServiceImpl implements UploadService {

    @Override
    public Result<UploadVO> uploadFile(MultipartFile request) {
        try {
            InputStream inputStream = request.getInputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024 * 100];
            int n;
            while (-1 != (n = inputStream.read(buffer))) {
                byteArrayOutputStream.write(buffer, 0, n);
            }
            byte[] bytes = byteArrayOutputStream.toByteArray();
            String filePath = new ClassPathResource(CONFIGFEIL).getFile().getAbsolutePath();
            // 1、加载配置文件，配置文件中的内容就是 tracker 服务的地址。
            ClientGlobal.init(filePath);
            // 2、创建一个 TrackerClient 对象。直接 new 一个。
            TrackerClient trackerClient = new TrackerClient();
            // 3、使用 TrackerClient 对象创建连接，获得一个 TrackerServer 对象。
            TrackerServer trackerServer = trackerClient.getConnection();
            // 5、创建一个 StorageClient 对象，需要两个参数 TrackerServer 对象、StorageServer 的引用
            StorageServer storageServer = null;
            StorageClient storageClient = new StorageClient(trackerServer, storageServer);
            String type = request.getName().substring(request.getName().lastIndexOf("."));
            String[] strings = storageClient.upload_file(bytes, type, null);
            List<String> list = Arrays.asList(strings);
            UploadVO uploadVO = new UploadVO();
            String url = SERVICEHOST + list.get(0) + "/" + list.get(1);
            uploadVO.setUrl(url);
            return ResultGenerator.success(uploadVO);
        } catch (Exception e){
            throw new RuntimeException("上传失败!");
        }
    }
}
