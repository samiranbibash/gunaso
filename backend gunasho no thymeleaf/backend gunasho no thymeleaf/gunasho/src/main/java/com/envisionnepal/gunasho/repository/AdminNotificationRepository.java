package com.envisionnepal.gunasho.repository;

import com.envisionnepal.gunasho.entities.Admin;
import com.envisionnepal.gunasho.entities.AdminNotification;
import com.envisionnepal.gunasho.entities.Gunasho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminNotificationRepository extends JpaRepository<AdminNotification, Long> {

    List<AdminNotification> findByGunashoGunashoId(Long gunashoId);
    List<AdminNotification> findByGunasho(Gunasho gunasho);

    int countByGunashoAndSeenStatus(Gunasho gunasho, boolean seenStatus);
}