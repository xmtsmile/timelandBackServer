package com.study.note.controller;

import io.minio.*;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

@RestController
@RequestMapping("/upload")
@Api("文件")
public class FileUploaderController {

    //MinIO上传
    @PostMapping("/fileupload")
    public void fileupload(MultipartFile file) {
        try {
            MinioClient client = MinioClient.builder().endpoint("http://127.0.0.1:9000").credentials("admin", "12345678").build();

            boolean isExist = client.bucketExists(BucketExistsArgs.builder().bucket("burger").build());
            if(isExist) {
                System.out.println("Bucket already exists.");
            } else {
                client.makeBucket(MakeBucketArgs.builder().bucket("burger").build());
            }

//            File file = new File("E:/text.txt");
            InputStream inputStream = file.getInputStream();
            client.putObject(PutObjectArgs.builder().bucket("burger").object("ham")
                    .stream(inputStream, inputStream.available(), -1).contentType("text/plain").build());
            System.out.println("文件已经成功上传");
        }
        catch(Exception e) {
            System.out.println("Error occurred: " + e);
        }
    }

    //MinIO下载
    @GetMapping("/fileload")
    public void fileload(@RequestBody Map<String, Object> params){
        try {
            String bucketName = params.get("bucketName").toString();
            String fileName = params.get("fileName").toString();

            MinioClient client = MinioClient.builder().endpoint("http://127.0.0.1:9000").credentials("admin", "12345678").build();

            client.downloadObject(DownloadObjectArgs.builder().bucket(bucketName).object(fileName).filename("E:/file图片.jpg").build());
        }
        catch(Exception e) {
            System.out.println("Error occurred: " + e);
        }
    }

    //附件上传
    @PostMapping ("fileupload2")
    public String fileupload2(@RequestParam MultipartFile file){
        try {
            long startTime=System.currentTimeMillis();
            System.out.println( "fileName：" + file.getOriginalFilename());
            String path= "D:/" + file.getOriginalFilename();

            File newFile= new File(path);
            file.transferTo(newFile);
            long endTime=System.currentTimeMillis();
            System.out.println("采用file.Transto的运行时间：" + String.valueOf(endTime-startTime) + "ms");
        }
        catch(Exception e) {
            System.out.println("Error occurred: " + e);
        }
        return "/success";
    }

    //附件下载
    @PostMapping ("fileload2")
    public HttpServletResponse download(HttpServletResponse response) {
        try {
            String path= "E:/text.txt";
            File file = new File(path);
            String filename = file.getName();
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();

            response.reset();

            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream outputStream  = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            outputStream.write(buffer);
            outputStream.flush();
            outputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return response;
    }
}
