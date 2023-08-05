package com.envisionnepal.gunasho.controller;


import java.util.*;
import java.util.stream.Collectors;

import com.envisionnepal.gunasho.dto.AdminNotificationDto;
import com.envisionnepal.gunasho.entities.AdminNotification;
import com.envisionnepal.gunasho.repository.AdminRepository;
import com.envisionnepal.gunasho.responseandregister.ApiResponse;
import com.envisionnepal.gunasho.service.AdminNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/gunasho")
public class AdminNotificationController {

    @Autowired
    private AdminNotificationService adminNotificationService;

    @Autowired
    private AdminRepository adminRepository;

    // create
//    @PostMapping("/admin/{id}/adminNotifications")
//    public ResponseEntity<AdminNotificationDto> createAdminNotification(@RequestBody AdminNotificationDto adminNotificationDto,
//                                                                        @PathVariable("id") Long id) {
//        AdminNotificationDto createdAdminNotification = adminNotificationService.createAdminNotification(adminNotificationDto, id);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdAdminNotification);
//    }
//
//
//    // update
//    @PutMapping("/adminNotifications/{adminNotificationId}")
//    public ResponseEntity<AdminNotificationDto> updateAdminNotification(@RequestBody AdminNotificationDto adminNotificationDto,
//                                                                        @PathVariable Long adminNotificationId) {
//
//        AdminNotificationDto updateAdminNotification = this.adminNotificationService.updateAdminNotification(adminNotificationDto, adminNotificationId);
//        return new ResponseEntity<AdminNotificationDto>(updateAdminNotification, HttpStatus.OK);
//    }
//
//    // delete
//    @DeleteMapping("</adminNotifications/{adminNotificationId}")
//    public ResponseEntity<ApiResponse> deleteAdminNotification(@PathVariable Long adminNotificationId) {
//        this.adminNotificationService.deleteAdminNotification(adminNotificationId);
//        return new ResponseEntity<ApiResponse>(new ApiResponse("AdminNotification is deleted sucessfully", true), HttpStatus.OK);
//    }
//
//    @GetMapping("/adminNotifications/{adminNotificationId}")
//    public ResponseEntity<AdminNotificationDto> getAdminNotification(@PathVariable Long adminNotificationId) {
//        AdminNotificationDto adminNotificationDto = this.adminNotificationService.getAdminNotificationById(adminNotificationId);
//        return new ResponseEntity<AdminNotificationDto>(adminNotificationDto, HttpStatus.OK);
//    }
//
//
//    @GetMapping("/adminNotifications")
//    public ResponseEntity<List<AdminNotificationDto>> getAllAdminNotifications()
//    {
//        List <AdminNotificationDto> adminNotifications = this.adminNotificationService.getAllAdminNotifications();
//        Collections.sort(adminNotifications, Comparator.comparing(AdminNotificationDto::getDateAndTime).reversed());
//        return ResponseEntity.ok(adminNotifications);
//
//    }
//
//
//    // get by user
//    @GetMapping("/admin/{id}/adminNotifications")
//    public ResponseEntity<List<AdminNotificationDto>> getAdminNotificationsByUser(@PathVariable("id") Long id) {
//        List<AdminNotificationDto> adminNotifications = this.adminNotificationService.getAdminNotificationsByAdmin(id);
//        return new ResponseEntity<List<AdminNotificationDto>>(adminNotifications, HttpStatus.OK);
//    }
    @PostMapping("/adminNotification/{gunashoId}")
    public ResponseEntity<AdminNotification> createAdminNotificationForGunasho(
            @RequestBody AdminNotificationDto adminNotificationDto,
            @PathVariable Long gunashoId
    ) {
        AdminNotification createdNotification = adminNotificationService.createAdminNotificationForGunasho(gunashoId, adminNotificationDto);
        return new ResponseEntity<>(createdNotification, HttpStatus.CREATED);
    }

    @GetMapping("/adminNotification/{gunashoId}")
    public ResponseEntity<List<AdminNotification>> getNotificationsByGunashoId(@PathVariable Long gunashoId) {
        List<AdminNotification> notifications = adminNotificationService.getNotificationsByGunashoId(gunashoId);
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }

    @GetMapping("/adminNotification/user/{userId}")
    public ResponseEntity<Map<String, Object>> getNotificationsByUserId(@PathVariable Long userId) {
        Map<String, Object> response = adminNotificationService.getNotificationsByUserId(userId);
        List<AdminNotification> notifications = (List<AdminNotification>) response.get("notifications");

        List<AdminNotification> sortedNotifications = notifications.stream()
                .sorted(Comparator.comparing(AdminNotification::getDateAndTime).reversed())
                .collect(Collectors.toList());
        response.put("notifications", sortedNotifications);

        if (sortedNotifications.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(response);
    }
    @PutMapping("/user/{userId}/seenStatus")
    public ResponseEntity<Map<String, String>> markNotificationsAsSeenForUser(@PathVariable Long userId) {
        adminNotificationService.updateSeenStatusForNotificationsByUserId(userId);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Notifications status updated");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/user/{userId}/unseennumber")
    public Map<String, Integer> getUnseenNotificationCount(@PathVariable Long userId) {
        Map<String, Integer> response = new HashMap<>();
        int unseenCount = adminNotificationService.getUnseenNotificationCount(userId);
        response.put("unseenCount", unseenCount);
        return response;
    }

}