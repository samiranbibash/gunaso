package com.envisionnepal.gunasho.file;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/gunasho/authenticate")
public class FileController {

    @Autowired
    FileService fileService;
    @Autowired
    VariableEntityRepository variableEntityRepository;

    @GetMapping(value = "/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(
            @PathVariable("imageName") String imageName,
            HttpServletResponse response
    ) throws IOException {

        InputStream resource = this.fileService.getResource(uploaddir(), imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream())   ;
    }

    public String uploaddir() throws IOException {
//	    String uploadDir = "/opt/images";

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

    @GetMapping("/images")
    public ResponseEntity<Map<String, Object>> getAllImages() {
        Map<String, Object> imageMap = new HashMap<>();
        List<VariableEntity> variableEntities = variableEntityRepository.findAll();

        for (VariableEntity variableEntity : variableEntities) {
            if (variableEntity.getProfImg1() != null) {
                imageMap.put("President", variableEntity.getProfImg1());
            }

            if (variableEntity.getProfImg2() != null) {
                imageMap.put("Vice President", variableEntity.getProfImg2());
            }

            if (variableEntity.getProfImg3() != null) {
                imageMap.put("CAO", variableEntity.getProfImg3());
            }

            List<String> bannerImages = new ArrayList<>();
            if (variableEntity.getBannerImg1() != null) {
                bannerImages.add(variableEntity.getBannerImg1());
            }
            if (variableEntity.getBannerImg2() != null) {
                bannerImages.add(variableEntity.getBannerImg2());
            }
            if (variableEntity.getBannerImg3() != null) {
                bannerImages.add(variableEntity.getBannerImg3());
            }

            if (!bannerImages.isEmpty()) {
                imageMap.put("Banners", bannerImages);
            }
        }

        return new ResponseEntity<>(imageMap, HttpStatus.OK);
    }




//    @PostMapping("/process-update/{variableId}")
//    public List<ImageResponse> updateForm(@ModelAttribute VariableEntityDto updatedVariableEntity,
//                                          @PathVariable Long variableId,
//                                          Model model, HttpSession session) throws IOException {
//        List<ImageResponse> images = new ArrayList<>();
//
//        try {
//            VariableEntity variableEntity = this.variableEntityRepository.findById(variableId).orElse(null);
//            if (variableEntity != null) {
//
//
//                images.add(new ImageResponse("ProfImg1", variableEntity.getProfImg1()));
//                images.add(new ImageResponse("ProfImg2", variableEntity.getProfImg2()));
//                images.add(new ImageResponse("ProfImg3", variableEntity.getProfImg3()));
//                images.add(new ImageResponse("BannerImg1", variableEntity.getBannerImg1()));
//                images.add(new ImageResponse("BannerImg2", variableEntity.getBannerImg2()));
//                images.add(new ImageResponse("BannerImg3", variableEntity.getBannerImg3()));
//            }
//
//            this.variableEntityRepository.save(variableEntity);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return images;
//    }


    @PostMapping("/process-update/{variableId}")
    public String updateForm(@ModelAttribute VariableEntityDto updatedVariableEntity,
                             @PathVariable Long variableId,
                             Model model, HttpSession session) throws IOException {
        try {
            VariableEntity variableEntity = this.variableEntityRepository.findById(variableId).orElse(null);
            if (variableEntity != null) {
                if (updatedVariableEntity.getProfImg1() != null && !updatedVariableEntity.getProfImg1().isEmpty()) {
                    String uniqueFileName = variableEntity.getVariableId() + "_president";
                    String pictureFilename = this.fileService.uploadImg(uploaddir(), updatedVariableEntity.getProfImg1(), uniqueFileName + "_ProfImg1");
                    variableEntity.setProfImg1(pictureFilename);
                }

                if (updatedVariableEntity.getProfImg2() != null && !updatedVariableEntity.getProfImg2().isEmpty()) {
                    String uniqueFileName = variableEntity.getVariableId() + "_vicePresident";
                    String pictureFilename = this.fileService.uploadImg(uploaddir(), updatedVariableEntity.getProfImg2(), uniqueFileName + "_ProfImg2");
                    variableEntity.setProfImg2(pictureFilename);
                }

                if (updatedVariableEntity.getProfImg3() != null && !updatedVariableEntity.getProfImg3().isEmpty()) {
                    String uniqueFileName = variableEntity.getVariableId() + "_CAO";
                    String pictureFilename = this.fileService.uploadImg(uploaddir(), updatedVariableEntity.getProfImg3(), uniqueFileName + "_ProfImg3");
                    variableEntity.setProfImg3(pictureFilename);
                }

                if (updatedVariableEntity.getBannerImg1() != null && !updatedVariableEntity.getBannerImg1().isEmpty()) {
                    String uniqueFileName = variableEntity.getVariableId() + "_banner1";
                    String pictureFilename = this.fileService.uploadImg(uploaddir(), updatedVariableEntity.getBannerImg1(), uniqueFileName + "_BannerImg1");
                    variableEntity.setBannerImg1(pictureFilename);
                }


                if (updatedVariableEntity.getBannerImg2() != null && !updatedVariableEntity.getBannerImg2().isEmpty()) {
                    String uniqueFileName = variableEntity.getVariableId() + "_banner2";
                    String pictureFilename = this.fileService.uploadImg(uploaddir(), updatedVariableEntity.getBannerImg2(), uniqueFileName + "_BannerImg2");
                    variableEntity.setBannerImg2(pictureFilename);
                }

                if (updatedVariableEntity.getBannerImg3() != null && !updatedVariableEntity.getBannerImg3().isEmpty()) {
                    String uniqueFileName = variableEntity.getVariableId() + "_banner3";
                    String pictureFilename = this.fileService.uploadImg(uploaddir(), updatedVariableEntity.getBannerImg3(), uniqueFileName + "_BannerImg3");
                    variableEntity.setBannerImg3(pictureFilename);
                }

            }

            this.variableEntityRepository.save(variableEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Success";
    }


}
