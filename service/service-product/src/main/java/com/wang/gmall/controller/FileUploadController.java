package com.wang.gmall.controller;

import com.wang.gmall.result.Result;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 微笑
 * @date 2020/7/15 20:04
 */
@RestController
@CrossOrigin
@RequestMapping("/admin/product")
public class FileUploadController {

    @Value("${fileServer.url}")
    private String fileUrl;

    @RequestMapping("fileUpload")
    public Result fileUpload(MultipartFile file) {

        String path = this.getClass().getResource("/tracker.conf").getPath();
        String resultPath = null;
        //初始化
        try {
            ClientGlobal.init(path);
            //创建tracker客户端
            TrackerClient trackerClient = new TrackerClient();

            //获取tracker连接
            TrackerServer trackerServer = trackerClient.getConnection();

            //创建storageClient1
            StorageClient1 storageClient1 = new StorageClient1(trackerServer, null);
            //获取文件后缀名
            String originalFilename = file.getOriginalFilename();
            int lastIndexOf = originalFilename.lastIndexOf(".");
            String extName = originalFilename.substring(lastIndexOf);

            resultPath = storageClient1.upload_appender_file1(file.getBytes(), extName, null);


            resultPath = fileUrl + resultPath;


        } catch (Exception e) {
            e.printStackTrace();
        }


        return Result.ok(resultPath);
    }
}
