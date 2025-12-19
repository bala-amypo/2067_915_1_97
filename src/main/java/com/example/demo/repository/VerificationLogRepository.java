package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.example.demo.entity.VerificationLog;

public interface VerificationLogRepository
        extends JpaRepository<VerificationLog, Long> {

    List<VerificationLog> findByCertificateId(Long certificateId);
}
