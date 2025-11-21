package com.example.backendConsultaIP.dto;

import lombok.Data;
import java.time.Instant;

@Data
public class IpLogDto {
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
    private Instant createdAt;
}

