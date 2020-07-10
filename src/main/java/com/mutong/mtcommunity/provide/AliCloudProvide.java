package com.mutong.mtcommunity.provide;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;
import java.sql.Date;
import java.util.UUID;

/**
 * @description:
 * @Author: gengchen.jing@yoyi.com.cn
 * @Date: 2020-07-08 21:01
 */
@Component
public class AliCloudProvide {
    @Value("${community.Mybacket.name}")
    private String bucketName;
    @Value("${community.endpoint}")
    private String endpoint;
    @Value("${community.accessKeyId}")
    private String accessKeyId;
    @Value("${community.accessKeySecret}")
    private String accessKeySecret;

    private  String key = ""+System.currentTimeMillis();

    public  String uploadFile(InputStream inputStream,String fileType,String fileName) throws IOException {
        //创建oss客户端
        OSS client = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            //key为文件的名字
            key+= UUID.randomUUID().toString()+fileName;
            //上传至oss服务器
            client.putObject(bucketName, key, inputStream);
            /**
             * 获取图片url
             */
            Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 3);
            URL url = client.generatePresignedUrl(bucketName, key, expiration);
            if (url != null) {
                return url.toString();
            }
            return  url.toString();
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message: " + oe.getErrorCode());
            System.out.println("Error Code:       " + oe.getErrorCode());
            System.out.println("Request ID:      " + oe.getRequestId());
            System.out.println("Host ID:           " + oe.getHostId());
            return null;
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ce.getMessage());
            return null;
        } finally {
            client.shutdown();
        }
    }

    private static File createSampleFile() throws IOException {
        File file = File.createTempFile("oss-java-sdk-", ".png");
        file.deleteOnExit();

        Writer writer = new OutputStreamWriter(new FileOutputStream(file));
        writer.write("abcdefghijklmnopqrstuvwxyz\n");
        writer.write("0123456789011234567890\n");
        writer.close();

        return file;
    }

    private static void displayTextInputStream(InputStream input) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        while (true) {
            String line = reader.readLine();
            if (line == null) break;

            System.out.println("\t" + line);
        }
        System.out.println();

        reader.close();
    }


}
