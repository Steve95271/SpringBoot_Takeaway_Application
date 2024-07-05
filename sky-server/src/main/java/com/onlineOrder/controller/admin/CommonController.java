package com.onlineOrder.controller.admin;

import com.onlineOrder.constant.MessageConstant;
import com.onlineOrder.result.Result;
import com.onlineOrder.utils.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * 通用API
 */
@RestController
@RequestMapping("/admin/common")
@Api("Common Api")
@Slf4j
public class CommonController {

    @Autowired
    private AliOssUtil aliOssUtil;

    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public Result<String> uploadFile(MultipartFile file) {
        log.info("上传文件: {}", file.getOriginalFilename());

        try {
            //获取原始文件名
            String originalFilename = file.getOriginalFilename();

            //截取原始文件名后缀
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

            //用UUID构建新的文件名防止遇到名字一样的文件被覆盖
            String objectName = UUID.randomUUID() + extension;

            String filepath = aliOssUtil.upload(file.getBytes(), objectName);
            return Result.success(filepath);
        } catch (IOException e){
            log.info("文件上传失败: {}", e);
        }

        return Result.error(MessageConstant.UPLOAD_FAILED);
    }
}
