package com.wang.gmall.test;


import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

import java.io.IOException;

/**
 * @author 微笑
 * @date 2020/7/15 18:28
 */

public class FileUploadTest {

    public static void main(String[] args) throws IOException, MyException {
        FileUploadTest fileUploadTest = new FileUploadTest();
        fileUploadTest.fileUpload();
    }

    public void fileUpload() throws IOException, MyException {
        String path = this.getClass().getResource("/tracker.conf").getPath();

        //初始化
        ClientGlobal.init(path);

        //创建tracker客户端
        TrackerClient trackerClient = new TrackerClient();

        //获取tracker连接
        TrackerServer trackerServer = trackerClient.getConnection();

        //创建storageClient1
        StorageClient1 storageClient1 = new StorageClient1(trackerServer, null);

        String[] resultPath = storageClient1.upload_appender_file("D:\\qq.jpg", "jpg", null);
        for (String s : resultPath) {
            System.out.println(s);
        }

    }

}
