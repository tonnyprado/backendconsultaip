package com.example.backendConsultaIP.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Table(name = "ip_logs")
@Data
public class IpLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private String ip;
    private String city;
    private String country;
    private String region;
    private String isp;
    private String threatLevel;
    private String timeZone;

    private Double latitude;
    private Double longitude;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = Instant.now();
    }
}

