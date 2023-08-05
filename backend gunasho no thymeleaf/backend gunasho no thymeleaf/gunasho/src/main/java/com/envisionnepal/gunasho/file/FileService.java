package com.envisionnepal.gunasho.file;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Service
public interface FileService {

    public String uploadImg(String path, MultipartFile file, String uniqueFileName) throws IOException;

    InputStream getResource(String path, String fileName)throws IOException, FileNotFoundException;
}
