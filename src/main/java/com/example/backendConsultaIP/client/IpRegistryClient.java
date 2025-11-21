package com.example.backendConsultaIP.client;

import com.example.backendConsultaIP.dto.IpLogDto;
import com.example.backendConsultaIP.dto.WhoIsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class IpRegistryClient {

    private final RestTemplate restTemplate;

    private static final String IPWHO_URL = "https://ipwho.is/{ip}";

    public IpLogDto lookupFromExternalApi(String ip) {
        WhoIsResponse ext = restTemplate.getForObject(
                IPWHO_URL,
                WhoIsResponse.class,
                ip
        );

        if (ext == null || !ext.isSuccess()) {
            throw new RuntimeException("No se pudo obtener información para la IP: " + ip);
        }

        IpLogDto dto = new IpLogDto();

        dto.setIp(ext.getIp());
        dto.setType(ext.getType());
        dto.setCity(ext.getCity());
        dto.setRegion(ext.getRegion());
        dto.setCountry(ext.getCountry());
        dto.setLatitude(ext.getLatitude());
        dto.setLongitude(ext.getLongitude());

        if (ext.getConnection() != null) {
            dto.setIsp(ext.getConnection().getIsp());
        }

        if (ext.getTimezone() != null) {
            dto.setTimeZone(ext.getTimezone().getId());
        }

        String threat = "low";
        if (ext.getSecurity() != null) {
            if (ext.getSecurity().isProxy() || ext.getSecurity().isVpn() || ext.getSecurity().isTor()) {
                threat = "high";
            }
        }
        dto.setThreatLevel(threat);

        return dto;
    }
}
