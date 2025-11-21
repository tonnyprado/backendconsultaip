package com.example.backendConsultaIP.dto;

import lombok.Data;

@Data
public class WhoIsResponse {

    private boolean success;
    private String ip;
    private String type;

    private String continent;
    private String country;
    private String region;
    private String city;

    private Double latitude;
    private Double longitude;

    private Connection connection;
    private Timezone timezone;
    private Security security;

    @Data
    public static class Connection {
        private String isp;
    }

    @Data
    public static class Timezone {
        private String id;
    }

    // Con este creo el THREAT LEVEL ACUERDATE
    @Data
    public static class Security {
        private boolean proxy;
        private boolean vpn;
        private boolean tor;
    }
}
