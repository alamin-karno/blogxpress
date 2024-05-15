package com.alaminkarno.blogxpress.services.impl;

import com.alaminkarno.blogxpress.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

        String fileName = file.getOriginalFilename();

        String randomID = UUID.randomUUID().toString();

        String newFileName = randomID.concat(fileName.substring(fileName.lastIndexOf(".")));

        String filePath = path+ File.separator + newFileName;

        File newFile = new File(path);
        if (!newFile.exists()) {
            newFile.mkdir();
        }

        Files.copy(file.getInputStream(), Paths.get(filePath));

        return newFileName;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {

        String fullPath = path + File.separator + fileName;

        return new FileInputStream(fullPath);
    }
}
