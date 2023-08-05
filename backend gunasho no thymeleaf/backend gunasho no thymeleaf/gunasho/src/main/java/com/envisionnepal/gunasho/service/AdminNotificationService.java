package com.envisionnepal.gunasho.service;

import com.envisionnepal.gunasho.dto.AdminNotificationDto;
import com.envisionnepal.gunasho.entities.AdminNotification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface AdminNotificationService {
    AdminNotification createAdminNotificationForGunasho(Long gunashoId, AdminNotificationDto adminNotificationDto);
    List<AdminNotification> getNotificationsByGunashoId(Long gunashoId);
    public Map<String, Object> getNotificationsByUserId(Long userId);
    public void updateSeenStatusForNotificationsByUserId(Long userId);
    int getUnseenNotificationCount(Long userId);
}
