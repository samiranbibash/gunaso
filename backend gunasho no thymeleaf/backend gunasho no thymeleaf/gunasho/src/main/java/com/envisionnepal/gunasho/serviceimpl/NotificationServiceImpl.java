//package com.envisionnepal.gunasho.serviceimpl;
//
//import java.time.LocalDate;
//import java.time.ZoneId;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//import java.util.stream.Collectors;
//
//
//import com.envisionnepal.gunasho.dto.NotificationDto;
//import com.envisionnepal.gunasho.entities.Notification;
//import com.envisionnepal.gunasho.entities.User;
//import com.envisionnepal.gunasho.exception.ResourceNotFoundException;
//import com.envisionnepal.gunasho.repository.NotificationRepository;
//import com.envisionnepal.gunasho.repository.UserRepository;
//import com.envisionnepal.gunasho.service.NotificationService;
//import org.modelmapper.ModelMapper;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//
//@Service
//public class NotificationServiceImpl implements NotificationService {
//
//    @Autowired
//    private NotificationRepository notificationRepository;
//
//    @Autowired
//    private ModelMapper modelMapper;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public NotificationDto createNotification(NotificationDto notificationDto, Long id) {
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("User", "User id", id));
//        Notification notification = modelMapper.map(notificationDto, Notification.class);
//        notification.setDateAndTime(new Date());
//        notification.setUser(user);
//        Notification addedNotification = notificationRepository.save(notification);
//        return modelMapper.map(addedNotification, NotificationDto.class);
//    }
//
//    @Override
//    public NotificationDto updateNotification(NotificationDto notificationDto, Long notif_id) {
//        Notification notification = this.notificationRepository.findById(notif_id)
//                .orElseThrow(() -> new ResourceNotFoundException("Notification", "Notification Id", notif_id));
//
//        notification.setContent(notificationDto.getContent());
//        notification.setTitle(notificationDto.getTitle());
//        notification.setSeen(notificationDto.isSeen());
//
//        Notification updatedNotification = this.notificationRepository.save(notification);
//        return this.modelMapper.map(updatedNotification, NotificationDto.class);
//    }
//
//    @Override
//    public void deleteNotification(Long notificationId) {
//        Notification notification = this.notificationRepository.findById(notificationId)
//                .orElseThrow(() -> new ResourceNotFoundException("Notification", "Notification id", notificationId));
//        this.notificationRepository.delete(notification);
//    }
//
//    @Override
//    public NotificationDto getNotificationById(Long notificationId) {
//        Notification notification = this.notificationRepository.findById(notificationId)
//                .orElseThrow(() -> new ResourceNotFoundException("Notification", "notification id", notificationId));
//        return this.modelMapper.map(notification, NotificationDto.class);
//
//    }
//
//    @Override
//    public List<NotificationDto> getAllNotifications() {
//        List<Notification> notifications = this.notificationRepository.findAll();
//        List<NotificationDto> notificationDtos = notifications.stream().map((notification) -> this.modelMapper.map(notification, NotificationDto.class))
//                .collect(Collectors.toList());
//
//        return notificationDtos;
//    }
//
//    @Override
//    public List<NotificationDto> getNotificationsByUser(Long userId) {
//        User user = this.userRepository.findById(userId)
//                .orElseThrow(() -> new ResourceNotFoundException("User ", "userId ", userId));
//        List<Notification> notifications = this.notificationRepository.findByUser(user);
//
//        List<NotificationDto> notificationDtos = notifications.stream().map((notification) -> this.modelMapper.map(notification, NotificationDto.class))
//                .collect(Collectors.toList());
//
//        return notificationDtos;
//    }
//
//
//    public boolean areAllNotificationsSeenByUser(Long userId) {
//        User user = this.userRepository.findById(userId)
//                .orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
//
//        List<Notification> notifications = this.notificationRepository.findByUser(user);
//
//        boolean allSeen = notifications.stream()
//                .allMatch(notification -> notification.isSeen());
//
//        return allSeen;
//    }
//
//
//    private static final Logger logger = LogManager.getLogger(NotificationServiceImpl.class);
//
//
//    @Override
//    public List<NotificationDto> updateNotificationsByUserId(Long userId) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
//
//        List<Notification> notifications = notificationRepository.findByUser(user);
//
//        List<NotificationDto> notificationDtos = notifications.stream()
//                .map(notification -> {
//                    notification.setSeen(true);
//                    return modelMapper.map(notification, NotificationDto.class);
//                })
//                .collect(Collectors.toList());
//
//        notificationRepository.saveAll(notifications);
//
//        return notificationDtos;
//    }
//
//
//
//}
