package com.example.backendConsultaIP.repository;

import com.example.backendConsultaIP.entity.IpLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface IpLogRepository extends JpaRepository<IpLog, Long> {
    Optional<IpLog> findByIp(String ip);
}

