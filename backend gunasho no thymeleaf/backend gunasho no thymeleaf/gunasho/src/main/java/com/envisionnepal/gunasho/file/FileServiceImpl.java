package com.envisionnepal.gunasho.file;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileServiceImpl implements FileService{

    public String uploaddir() throws IOException {

        String os = System.getProperty("os.name").toLowerCase();
        String uploadDir;

        if (os.contains("win")) {
            uploadDir = "static/image/";
        } else {
            uploadDir = "/opt/images";
        }
        Resource resource = new FileSystemResource(uploadDir);
        String resourcePath = URLDecoder.decode(resource.getFile().getAbsolutePath(), "UTF-8");
        resourcePath = resourcePath.replace(File.separator, "/");
        File dir = new File(resourcePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return resourcePath;
    }

    //for the unique name with id
    public String uploadImg(String path, MultipartFile file, String uniqueFileName) {

        try {

            String name = file.getOriginalFilename();

            String fileName = uniqueFileName + name.substring(name.lastIndexOf("."));

            String filePath = uploaddir() + File.separator + fileName;

            File f= new File(uploaddir());
            if (!f.exists()) {
                f.mkdir();
            }


            Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
            System.out.println(filePath);
            // Return the unique filename
            System.out.println(fileName);

            return fileName;
        }

        catch (Exception e) {
            // TODO: handle exception
        }
        return "not uploaded";}


    @Override
    public InputStream getResource(String path, String fileName) throws IOException, FileNotFoundException {

        String fullPath = uploaddir() + File.separator + fileName;
        InputStream is = new FileInputStream(fullPath);
        // db logic to return inpustream
        return is;
    }

}
