package com.envisionnepal.gunasho.file;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class VariableEntityDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long variableId ;

    private MultipartFile ProfImg1;

    private MultipartFile ProfImg2;

    private MultipartFile ProfImg3;

    private MultipartFile BannerImg1;

    private MultipartFile BannerImg2;

    private MultipartFile BannerImg3;

//    private List<String> profImages;
//    private List<String> bannerImages;

}