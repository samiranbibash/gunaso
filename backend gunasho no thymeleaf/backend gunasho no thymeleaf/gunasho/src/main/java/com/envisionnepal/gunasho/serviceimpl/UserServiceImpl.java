/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.envisionnepal.gunasho.serviceimpl;

import com.envisionnepal.gunasho.entities.User;
import com.envisionnepal.gunasho.exception.ResourceNotFoundException;
import com.envisionnepal.gunasho.repository.UserRepository;
import com.envisionnepal.gunasho.service.JwtService;
import com.envisionnepal.gunasho.service.UserService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author User
 */
@Service
public class UserServiceImpl implements UserService{
     @Autowired
    private UserRepository userepo;
     @Autowired
     private PasswordEncoder passwordEncoder;
     @Autowired
     private JwtService jwtservice;
    @Override
    public String add(User user) {
        Optional<User> theUser = userepo.findByMobileNumber(user.getMobileNumber());
        if (theUser.isPresent()){
           System.out.print(user.getMobileNumber());
           return "User Exist" ; 
                   
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User u = userepo.save(user);
        if(u== null){
            return "User not created" ;
        }else{
        return "User Created, User will be verified within 72 Hours" ;
        }
    }
    


    @Override
    public List<User> getAllUsers() {
         return userepo.findAll().stream().map(user-> new User(user)).collect(Collectors.toList());
//                .stream()
//                .map(user -> new User(
//                        user).collect(Collectors.toList());
     }

    @Override
    public void delete(String phone) {
        userepo.deleteByMobileNumber(phone);  
    }

    @Override
    public User  getUser(String phone) {
         Optional<User> theUser = userepo.findByMobileNumber(phone);
         if(theUser.isEmpty()){
          
             return  null ; 
         }
         
         return theUser.get();
         
    }

    @Override
    public String updatePassword(Long id,String password) {
         Optional<User> theuser = userepo.findById(id);
        if (theuser.isPresent()) {
            User existing = theuser.get();
            existing.setPassword(passwordEncoder.encode(password));
           
             
             // Save the updated entity
             System.out.print("before  updating ");
        User  u =     userepo.save(existing);
          if(u !=null){
       
           return "Password change success";
          }else{
              return "Password change Failed ";
          }
        } else {
           return "Fail to get User";
        }
        
    }

    @Override
    public User forgetPassword(User userDto, String mobileNumber) {
        User user = this.userepo.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("User", "mobileNumber", mobileNumber));

        // Update the password only if it's provided in the UserDto
        if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
            String encryptedPassword = this.passwordEncoder.encode(userDto.getPassword());
            user.setPassword(encryptedPassword);
        }

        User updatedUser = this.userepo.save(user);

        return updatedUser;
    }
   
    
    @Override
    public String verifyUserAccount(Long id, boolean status) {
        Optional<User> theuser = userepo.findById(id);
         if (theuser.isPresent()) {
            User existing = theuser.get();
            existing.setVerfication(status);
           
             
             // Save the updated entity
             System.out.print("before  updating ");
        User  u =     userepo.save(existing);
          if(u.isVerfication() ==true ){
       
           return "Account Verified status success";
          }else{
              return "Account Verified status Failed ";
          }
        } else {
           return "Fail to get User";
        }
    }
//    public String uploadImage( MultipartFile file) throws IOException {
//		// file name
//		String fileName = file.getOriginalFilename();
//		String path;
//                String os = System.getProperty("os.name").toLowerCase();
//                 if (os.contains("win")) {
//            path = "/static/images/";
//        }else{
//                    path = "/opt/images"; 
//                 }
//		//user.getId from here and pass to the path
//		//it is generating random name
//		
//		String randomID = UUID.randomUUID().toString();
//         String concat = randomID.concat(fileName);
//		
//	    // generate file name using user ID and original filename
//	    //String fileName = user.getId() + "_" + name ;
//		// fullpath 
//                  Path currentPath = Paths.get("");
//        String absolutePath = currentPath.toAbsolutePath().toString();
//		String filePath = absolutePath +path +File.separator+ concat ;
//		System.out.println("file path above line code"+filePath);
//		//
//
//		// create folder if not crteated
//		File f = new File(path);
//		if (!f.exists()) {
//			 Files.createDirectories(Path.of(path));
//			
//		}
//		// file copy
//                
//		
//		Files.copy(file.getInputStream(),Paths.get(filePath));
//		//System.out.println(name);
//		return concat;
//	}
 public String uploadImage( MultipartFile file) throws IOException {
		// file name
		String fileName = file.getOriginalFilename();
		String path;
                String os = System.getProperty("os.name").toLowerCase();
                 if (os.contains("win")) {
            path = "static/image";
        }else{
                    path = "/opt/images"; 
                 }
		//user.getId from here and pass to the path
		//it is generating random name
		
		String randomID = UUID.randomUUID().toString();
         String concat = randomID.concat(fileName);
		
	    // generate file name using user ID and original filename
	    //String fileName = user.getId() + "_" + name ;
		// fullpath 
		String filePath =path +File.separator+ concat ;
		System.out.println("file path above line code"+filePath);
		//

		// create folder if not crteated
		File f = new File(path);
		if (!f.exists()) {
			 Files.createDirectories(Path.of(path));
			
		}
		// file copy
                
		
		Files.copy(file.getInputStream(),Paths.get(filePath));
		//System.out.println(name);
		return concat;
	}

    @Override
    public String suspendUserAccount(Long id, boolean status) {
      Optional<User> theuser = userepo.findById(id);
         if (theuser.isPresent()) {
            User existing = theuser.get();
            existing.setIssuspend(status);
           
             
             // Save the updated entity
             System.out.print("before  updating ");
        User  u =     userepo.save(existing);
          if(u.isVerfication() ==true ){
       
           return "Account Verified status success";
          }else{
              return "Account Verified status Failed ";
          }
        } else {
           return "Fail to get User";
        }   }
    @Override
    public User getUserByMobileNumber(String mobileNumber) {
        Optional<User> userOptional = userepo.findByMobileNumber(mobileNumber);
        return userOptional.orElse(null);
    }


    
}
