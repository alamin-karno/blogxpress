package com.alaminkarno.blogxpress.controllers;

import com.alaminkarno.blogxpress.payloads.FileResponse;
import com.alaminkarno.blogxpress.services.FileService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("api/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    // POST: UPLOAD IMAGE
    @PostMapping("/upload")
    public ResponseEntity<FileResponse> uploadFile(@RequestParam("image") MultipartFile image) {

        String fileName = null;
        try {
            fileName = this.fileService.uploadImage(path,image);
        } catch (IOException e) {
            return new ResponseEntity<>(new FileResponse(fileName,"Image not uploaded due to error on server"),HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(new FileResponse(fileName,"Image is successfully uploaded"), HttpStatus.OK);
    }

    // GET: SERVE FILE
    @GetMapping(value = "/images/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable String imageName, HttpServletResponse response) {
        InputStream inputStream;
        try {
             inputStream = this.fileService.getResource(path,imageName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);

        try {
            StreamUtils.copy(inputStream,response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
