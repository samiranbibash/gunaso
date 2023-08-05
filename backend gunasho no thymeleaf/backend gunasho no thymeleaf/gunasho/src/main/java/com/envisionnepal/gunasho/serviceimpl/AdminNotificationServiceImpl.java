package com.envisionnepal.gunasho.serviceimpl;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import com.envisionnepal.gunasho.dto.AdminNotificationDto;
import com.envisionnepal.gunasho.entities.Admin;
import com.envisionnepal.gunasho.entities.AdminNotification;
import com.envisionnepal.gunasho.entities.Gunasho;
import com.envisionnepal.gunasho.exception.ResourceNotFoundException;
import com.envisionnepal.gunasho.repository.AdminNotificationRepository;
import com.envisionnepal.gunasho.repository.AdminRepository;
import com.envisionnepal.gunasho.repository.GuanashoRepository;
import com.envisionnepal.gunasho.service.AdminNotificationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AdminNotificationServiceImpl implements AdminNotificationService {

    @Autowired
    private AdminNotificationRepository adminNotificationRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private GuanashoRepository gunashoRepository;

    @Override
    public AdminNotification createAdminNotificationForGunasho(Long gunashoId, AdminNotificationDto adminNotificationDto) {
        Gunasho gunasho = gunashoRepository.findById(gunashoId).orElse(null);
        if (gunasho == null) {
            throw new IllegalArgumentException("Invalid Gunasho ID");
        }

        AdminNotification adminNotification = new AdminNotification();
        adminNotification.setTitle(adminNotificationDto.getTitle());
        adminNotification.setInformation(adminNotificationDto.getInformation());
        adminNotification.setDateAndTime(LocalDateTime.now());
        adminNotification.setSeenStatus(false);

        adminNotification.setGunasho(gunasho);


        return adminNotificationRepository.save(adminNotification);
    }

    @Override
    public List<AdminNotification> getNotificationsByGunashoId(Long gunashoId) {
        return adminNotificationRepository.findByGunashoGunashoId(gunashoId);
    }

    @Override
    public Map<String, Object> getNotificationsByUserId(Long userId) {
        List<Gunasho> gunashos = gunashoRepository.findByUserId(userId);
        Map<String, Object> response = new HashMap<>();

        if (gunashos == null || gunashos.isEmpty()) {
            response.put("notifications", Collections.emptyList());
            response.put("message", "No notifications found for the user.");
            return response;
        }
        List<AdminNotification> notifications = adminNotificationRepository.findByGunasho(gunashos.get(0));
        response.put("notifications", notifications);
        response.put("message", "Notifications fetched successfully.");
        return response;
    }
    @Override
    public void updateSeenStatusForNotificationsByUserId(Long userId) {
        List<Gunasho> gunashos = gunashoRepository.findByUserId(userId);

        if (gunashos == null || gunashos.isEmpty()) {
            throw new IllegalArgumentException("Invalid User ID or user not found.");
        }
        List<AdminNotification> notifications = adminNotificationRepository.findByGunasho(gunashos.get(0));
        for (AdminNotification notification : notifications) {
            notification.setSeenStatus(true);
        }

        adminNotificationRepository.saveAll(notifications);
    }
    @Override
    public int getUnseenNotificationCount(Long userId) {
        List<Gunasho> gunashos = gunashoRepository.findByUserId(userId);
        if (gunashos == null || gunashos.isEmpty()) {
            return 0;
        }
        return adminNotificationRepository.countByGunashoAndSeenStatus(gunashos.get(0), false);
    }
    }

//    @Override
//    public AdminNotificationDto createAdminNotification(AdminNotificationDto adminNotificationDto, Long id) {
//        Admin admin = adminRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Admin", "Admin id", id));
//        AdminNotification adminNotification = modelMapper.map(adminNotificationDto, AdminNotification.class);
//        adminNotification.setDateAndTime(new Date());
//        adminNotification.setAdmin(admin);
//
//        AdminNotification addedAdminNotification = adminNotificationRepository.save(adminNotification);
//        return modelMapper.map(addedAdminNotification, AdminNotificationDto.class);
//    }
//
//    @Override
//    public AdminNotificationDto updateAdminNotification(AdminNotificationDto adminNotificationDto, Long adminNotificationId) {
//        AdminNotification adminNotification = this.adminNotificationRepository.findById(adminNotificationId)
//                .orElseThrow(() -> new ResourceNotFoundException("AdminNotification", "AdminNotification Id", adminNotificationId));
//
//        adminNotification.setInformation(adminNotificationDto.getInformation());
//        adminNotification.setHistoryStatus(adminNotificationDto.isHistoryStatus());
//        adminNotification.setTitle(adminNotificationDto.getTitle());
//        adminNotification.setHistoryStatus(adminNotificationDto.isHistoryStatus());
//
//        AdminNotification updatedAdminNotification = this.adminNotificationRepository.save(adminNotification);
//        return this.modelMapper.map(updatedAdminNotification, AdminNotificationDto.class);
//    }
//
//    @Override
//    public void deleteAdminNotification(Long adminNotificationId) {
//        AdminNotification adminNotification = this.adminNotificationRepository.findById(adminNotificationId)
//                .orElseThrow(() -> new ResourceNotFoundException("AdminNotification", "AdminNotification id", adminNotificationId));
//        this.adminNotificationRepository.delete(adminNotification);
//    }
//
//    @Override
//    public AdminNotificationDto getAdminNotificationById(Long adminNotificationId) {
//        AdminNotification adminNotification = this.adminNotificationRepository.findById(adminNotificationId)
//                .orElseThrow(() -> new ResourceNotFoundException("AdminNotification", "adminNotification id", adminNotificationId));
//        return this.modelMapper.map(adminNotification, AdminNotificationDto.class);
//
//    }
//
//    @Override
//    public List<AdminNotificationDto> getAllAdminNotifications() {
//        List<AdminNotification> adminNotifications = this.adminNotificationRepository.findAll();
//        List<AdminNotificationDto> adminNotificationDtos = adminNotifications.stream().map((adminNotification) -> this.modelMapper.map(adminNotification, AdminNotificationDto.class))
//                .collect(Collectors.toList());
//
//        return adminNotificationDtos;
//    }
//
//    @Override
//    public List<AdminNotificationDto> getAdminNotificationsByAdmin(Long id) {
//        Admin admin = this.adminRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Admin ", "adminId ", id));
//        List<AdminNotification> adminNotifications = this.adminNotificationRepository.findByAdmin(admin);
//
//        List<AdminNotificationDto> adminNotificationDtos = adminNotifications.stream().map((adminNotification) -> this.modelMapper.map(adminNotification, AdminNotificationDto.class))
//                .collect(Collectors.toList());
//
//        return adminNotificationDtos;
//
//
//    }
