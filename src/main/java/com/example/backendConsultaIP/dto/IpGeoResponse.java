package com.example.backendConsultaIP.dto;

import lombok.Data;

@Data
public class IpGeoResponse {
    private String ip;
    private String version;
    private String city;
    private String region;
    private String country;
    private String isp;
    private Double latitude;
    private Double longitude;
    private String timezone;
    private String threatLevel;
}
