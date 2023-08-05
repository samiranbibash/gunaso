/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.envisionnepal.gunasho.controller;

import com.envisionnepal.gunasho.entities.Gunasho;
import com.envisionnepal.gunasho.responseandregister.GunashoRegister;
import com.envisionnepal.gunasho.entities.User;
import com.envisionnepal.gunasho.repository.GuanashoRepository;
import com.envisionnepal.gunasho.responseandregister.GunashoRequest;
import com.envisionnepal.gunasho.responseandregister.Response;
import com.envisionnepal.gunasho.service.JwtService;
import com.envisionnepal.gunasho.serviceimpl.GunashoServiceImpl;
import com.envisionnepal.gunasho.serviceimpl.UserServiceImpl;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author User
 */
@RestController
@RequestMapping("/gunasho")
public class GunashoController {

    @Autowired
    UserServiceImpl serviceimpl;
    
    @Autowired
    JwtService jwtservice;
    
     @Autowired
    GunashoServiceImpl gunashoserviceimpl;
     @Autowired
     GuanashoRepository repo;
     

    @Autowired
    public GunashoController(ResourceLoader resourceLoader) {
    }
     //only admin
//     @GetMapping(value = "/admin/getallgunasho" )
//     public ResponseEntity<List<Gunasho> >getgunasho(){
//
//        List< Gunasho> list = gunashoserviceimpl.getAllGunasho();
//         Map<String, Object> response = new HashMap<>();
//
//         if (!list.isEmpty()) {
//             response.put("gunashos", list);
//             response.put("message", "Success");
//             return new ResponseEntity<T>(response, HttpStatus.OK);
//         } else {
//             response.put("gunashos", new ArrayList<>());
//             response.put("message", "No gunashos found for the user");
//             return new ResponseEntity<T>(response, HttpStatus.OK);
//         }
//
//          return new ResponseEntity<>(list, HttpStatus.OK);
//     }

    @GetMapping(value = "/admin/getallgunasho")
    public ResponseEntity<Map<String, Object>> getgunasho() {
        List<Gunasho> list = gunashoserviceimpl.getAllGunasho();
        Map<String, Object> response = new HashMap<>();

        if (!list.isEmpty()) {
            response.put("message", "Success");
            response.put("gunashos:", list);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("gunasho", new ArrayList<>());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }


    @GetMapping(value = "/user/getagunasho")
    public ResponseEntity<Map<String, Object>> getGunasho(@RequestHeader(value = "Authorization") String authorization) {
        String token = authorization.replaceAll("Bearer", " ").trim();
        System.out.print("token after trim" + token);
        String username = jwtservice.extractUsernameFromToken(token);
        List<Gunasho> list = gunashoserviceimpl.getGunashobyuser(username);
        Map<String, Object> response = new HashMap<>();

        if (!list.isEmpty()) {
            list.sort(Comparator.comparing(Gunasho::getDateapplied).reversed());

            response.put("gunashos", list);
            response.put("message", "Success");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("gunashos", new ArrayList<>());
            response.put("message", "No gunashos found for the user");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }


    @PostMapping("/{gunashoId}/update-status/{iscompleted}")
    public ResponseEntity<String> updateCompletedStatusGunasho(@PathVariable Long gunashoId,@PathVariable String iscompleted) {
        gunashoserviceimpl.updateCompletedStatusInWorkOrder(gunashoId, iscompleted);
        return ResponseEntity.ok("Status updated successfully.");
    }

     //user access only
@PostMapping(value = "/user/addgunasho", consumes = {"multipart/form-data"})
public ResponseEntity<Response> addGunasho(@ModelAttribute GunashoRegister request,
                                           @RequestHeader(value = "Authorization") String authorization) throws IOException {
    System.out.print(authorization);
    String token = authorization.replaceAll("Bearer", " ").trim();
    System.out.print("token after trim" + token);
    String username = jwtservice.extractUsernameFromToken(token);
    System.out.println(username);
    User u = serviceimpl.getUser(username);
    Response response = new Response();
    String photoname = "no file";
    if (request.getPhotoUrl() != null) {
        photoname = serviceimpl.uploadImage(request.getPhotoUrl());
    }
    LocalDate ldate = LocalDate.now();
    LocalTime ltime = LocalTime.now();
    LocalDateTime uploaddate = LocalDateTime.of(ldate, ltime);

    Gunasho newgunasho = new Gunasho(
            u.getId(),
            request.getCategory(),
            request.getSubcategory(),
            request.getLocation(),
            request.getIncident(),
            request.getImpact(),
            request.getDetails(),
            uploaddate,
            photoname,
            "1"
    );
    Gunasho g = gunashoserviceimpl.add(newgunasho);
    if (g == null) {
        response.setMessage("Gunasho upload fail ");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    response.setMessage("Gunasho Upload is Successfull");
    return new ResponseEntity<>(response, HttpStatus.OK);
}


    //user and admin access
    @PutMapping(value = "/editgunasho/{id}")
    public ResponseEntity<Response> editGunasho(@ModelAttribute GunashoRegister request,

            @PathVariable(value = "id") Long id) throws IOException {

        Response response = new Response();

        String s = gunashoserviceimpl.updateGunasho(id, request);

        response.setMessage(s);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



//          @PutMapping(value = "/editgunashocompletedate/{id}")
//    public ResponseEntity<Response> editGunashocompletedate(
//
//            @PathVariable(value = "id") Long id) throws IOException {
//
//        Response response = new Response();
//
//        String s = gunashoserviceimpl.updateiscompletedate(id);
//
//        response.setMessage(s);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

     @GetMapping("/files/{fileName}")
public ResponseEntity<Resource> getFile(@PathVariable("fileName") String filename) throws IOException  {
     Path currentPath = Paths.get("");
        String absolutePath = currentPath.toAbsolutePath().toString();
      String relativePath = "static/image/" + filename;
        Path imagePath = Paths.get(absolutePath, relativePath);
        
        if (!Files.exists(imagePath)) {
           System.out.print("no file");
          return ResponseEntity.notFound().build();
        }else{
            String contentType = determineContentType(filename);
        Resource imageResource = new UrlResource(imagePath.toUri());

           return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(imageResource);
        }
    }
private String determineContentType(String imageName) {
        String extension = imageName.substring(imageName.lastIndexOf(".") + 1);
        switch (extension.toLowerCase()) {
            case "jpg":
            case "jpeg":
                return "image/jpeg";
            case "png":
                return "image/png";
            case "gif":
                return "image/gif";
            default:
                return MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }
    }

    //admin access only 
     @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<Response> delete(@PathVariable("id") Long id){
       Response response = new Response();
       repo.deleteByGunashoId(id);
       
        Optional<Gunasho> u = repo.findByGunashoId(id);
        if(u.isEmpty()){
            response.setMessage("Gunasho delete Unsuccessfull");
        }else{
            
            response.setMessage("Gunasho is Deleted");
        }
            
         return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    
}
