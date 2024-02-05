package com.lcwd.todo.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;

@RestController
@RequestMapping("/file")
public class FileController {

    Logger loggers = LoggerFactory.getLogger(FileController.class);
    @PostMapping("/single")
    public String uploadSingle(@RequestParam ("video")MultipartFile  file){
        loggers.info("Name : {}" ,file.getName());
        loggers.info("Content Type : {}" , file.getContentType());
        loggers.info("Original File Name : {}",file.getOriginalFilename());
        loggers.info("File Size : {}" ,file.getSize());
        //InputStream inputStream = file.getInputStream();
        return "File Test";
    }
    @PostMapping("/multiple")
    public String uploadMultiple(@RequestParam ("files") MultipartFile[] files){
        Arrays.stream(files).forEach(file->{
            loggers.info("File Name : {}" ,file.getOriginalFilename());
            loggers.info("Content Type : {}" , file.getContentType());
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");
            //Call service to upload files : and pass file object
        });
        return "Handling Multiple Files";
    }
    @GetMapping("/serve-image")
    public void serveImageHandler(HttpServletResponse response)  {
        try {
            InputStream fileInputStream = new FileInputStream("images/pexels-blaque-x-863963.jpg");
            response.setContentType(MediaType.IMAGE_PNG_VALUE);
            StreamUtils.copy(fileInputStream,response.getOutputStream());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
