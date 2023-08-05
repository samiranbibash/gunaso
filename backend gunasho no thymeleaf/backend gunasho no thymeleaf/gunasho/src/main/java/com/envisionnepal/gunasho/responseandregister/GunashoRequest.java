package com.envisionnepal.gunasho.responseandregister;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GunashoRequest {
    private String category;
    private String subcategory;
    private String location;
    private String incident;
    private String impact;
    private String details;
    private MultipartFile photoUrl;
    private String adminmessage;
    private String iscompleted;

}
