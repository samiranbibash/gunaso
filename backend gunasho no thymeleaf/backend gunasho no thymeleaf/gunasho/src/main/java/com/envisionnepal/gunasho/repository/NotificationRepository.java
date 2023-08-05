//package com.envisionnepal.gunasho.repository;
//
//import java.util.List;
//import java.util.Optional;
//
//import com.envisionnepal.gunasho.entities.Notification;
//import com.envisionnepal.gunasho.entities.User;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//
//public  interface NotificationRepository extends JpaRepository<Notification, Long> {
//
//    List<Notification> findByUser(User user);
//
////	List<Notification> findByUser(Long userId);
//
//    List<Notification> findByUsedBy(String usedBy);
//
//    Notification findByUserId(Long userId);
//
////	Optional<Notification> findByUser(Long userId);
//}