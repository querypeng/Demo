package store.pengfeng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import store.pengfeng.Service.UploadService;
import store.pengfeng.VO.UploadVO;
import store.pengfeng.common.response.Response;
import store.pengfeng.common.response.Result;
import store.pengfeng.common.utils.ResponseGenerator;

/**
 * @author pengfeng
 * @date 2018/10/27 0:36
 */
@RestController
@RequestMapping("CRM")
public class AdminCrmController {

    @Autowired
    private UploadService uploadService;

    @PostMapping("/upload/file")
    public Response<UploadVO> upload(@RequestBody MultipartFile request,String type)  {
        Result<UploadVO> result = uploadService.uploadFile(request,type);
        if (result.isSuccess()) {
            return ResponseGenerator.success(result.getTarget());
        } else {
            return ResponseGenerator.fail(result.getError().getCode(), result.getError().getMessage());
        }
    }


}
