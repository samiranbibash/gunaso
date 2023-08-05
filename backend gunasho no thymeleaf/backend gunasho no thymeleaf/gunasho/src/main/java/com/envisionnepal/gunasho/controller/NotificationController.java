//package com.envisionnepal.gunasho.controller;
//
//
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.List;
//
//import com.envisionnepal.gunasho.dto.NotificationDto;
//import com.envisionnepal.gunasho.entities.Notification;
//import com.envisionnepal.gunasho.entities.User;
//import com.envisionnepal.gunasho.exception.ResourceNotFoundException;
//import com.envisionnepal.gunasho.repository.NotificationRepository;
//import com.envisionnepal.gunasho.repository.UserRepository;
//import com.envisionnepal.gunasho.responseandregister.ApiResponse;
//import com.envisionnepal.gunasho.service.NotificationService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//
//@RestController
//@RequestMapping("/gunasho/authenticate")
//public class NotificationController {
//
//    @Autowired
//    private NotificationService notificationService;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private NotificationRepository notificationRepository;
//
//    @PostMapping("/user/{id}/notifications")
//    public ResponseEntity<NotificationDto> createNotification(@RequestBody NotificationDto notificationDto,
//                                                              @PathVariable("id") Long userId) {
//        NotificationDto createdNotification = notificationService.createNotification(notificationDto, userId);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdNotification);
//    }
//
//
//    // update
//    @PutMapping("/notifications/{notif_id}")
//    public ResponseEntity<NotificationDto> updateNotification(@RequestBody NotificationDto notificationDto,
//                                                              @PathVariable Long notif_id) {
//
//        NotificationDto updateNotification = this.notificationService.updateNotification(notificationDto, notif_id);
//        return new ResponseEntity<NotificationDto>(updateNotification, HttpStatus.OK);
//    }
//
//    // delete
//    @DeleteMapping("</notifications/{notif_id}")
//    public ResponseEntity<ApiResponse> deleteNotification(@PathVariable Long notif_id) {
//        this.notificationService.deleteNotification(notif_id);
//        return new ResponseEntity<ApiResponse>(new ApiResponse("Notification is deleted sucessfully", true), HttpStatus.OK);
//    }
//
//    // get by id
//    @GetMapping("/notifications/{notif_id}")
//    public ResponseEntity<NotificationDto> getNotification(@PathVariable Long notif_id) {
//        NotificationDto notificationDto = this.notificationService.getNotificationById(notif_id);
//        return new ResponseEntity<NotificationDto>(notificationDto, HttpStatus.OK);
//    }
//
//
//    @GetMapping("/notifications")
//    public ResponseEntity<List<NotificationDto>> getAllNotifications()
//    {
//        List <NotificationDto> notifications = this.notificationService.getAllNotifications();
//        return ResponseEntity.ok(notifications);
//
//    }
//    //with leatest on top
////		@GetMapping("/notifications")
////		public ResponseEntity<List<NotificationDto>> getAllNotifications() {
////		    List<NotificationDto> notifications = this.notificationService.getAllNotifications();
////
////		    // Sort the notifications based on the dateAndTime field in descending order
////		    Collections.sort(notifications, Comparator.comparing(NotificationDto::getDateAndTime).reversed());
////
////		    return ResponseEntity.ok(notifications);
////		}
//
//    // get by user
//    @GetMapping("/user/{id}/notifications")
//    public ResponseEntity<List<NotificationDto>> getNotificationsByUser(@PathVariable("id") Long userId) {
//        List<NotificationDto> notifications = this.notificationService.getNotificationsByUser(userId);
//
//        // Update the seen status of the user's notifications to true
//        for (NotificationDto notification : notifications) {
//            notification.setSeen(true);
//
//
//        }
//        //  Sort the notifications based on the dateAndTime field in descending order
//        Collections.sort(notifications, Comparator.comparing(NotificationDto::getDateAndTime).reversed());
//
//        return new ResponseEntity<List<NotificationDto>>(notifications, HttpStatus.OK);
//    }
//
//
//    @GetMapping("/seen/{userId}/notifications")
//    public ResponseEntity<ApiResponse> checkNotificationsByUser(@PathVariable("userId") Long id) {
//        User user = this.userRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("User", "userId", id));
//
//        List<Notification> notifications = this.notificationRepository.findByUser(user);
//
//        boolean allSeen = notifications.stream()
//                .allMatch(Notification::isSeen);
//
//        if (!allSeen) {
//            ApiResponse response = new ApiResponse("Not all notifications are seen", false);
//            return ResponseEntity.status(HttpStatus.OK).body(response);
//        } else {
//            ApiResponse response = new ApiResponse("All notifications are seen", true);
//            return ResponseEntity.status(HttpStatus.OK).body(response);
//        }
//    }
//
//
//
//
//    @PutMapping("/user/{id}/notifications/updateSeenStatus")
//    public ResponseEntity<List<NotificationDto>> updateNotificationsByUserId(@PathVariable("id") Long userId) {
//        List<NotificationDto> notifications = this.notificationService.updateNotificationsByUserId(userId);
//
//        // Update the seen status of the user's notifications to true
//        for (NotificationDto notification : notifications) {
//            notification.setSeen(true);
//
//
//        }
//
//        return new ResponseEntity<List<NotificationDto>>(notifications, HttpStatus.OK);
//    }
//
//
//
//
//}