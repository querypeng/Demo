package store.pengfeng.Service;

import org.springframework.web.multipart.MultipartFile;
import store.pengfeng.VO.UploadVO;
import store.pengfeng.common.response.Result;

public interface UploadService {

    Result<UploadVO> uploadFile(MultipartFile request);
}
