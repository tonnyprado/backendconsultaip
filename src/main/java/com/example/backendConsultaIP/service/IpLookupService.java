package com.example.backendConsultaIP.service;

import com.example.backendConsultaIP.client.IpRegistryClient;
import com.example.backendConsultaIP.dto.*;
import com.example.backendConsultaIP.entity.IpLog;
import com.example.backendConsultaIP.repository.IpLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IpLookupService {

    private final IpLogRepository ipLogRepository;
    private final IpRegistryClient ipRegistryClient;

    @Transactional
    public IpLookupResponse lookupIp(String ip) {

        return ipLogRepository.findByIp(ip)
                .map(existing -> new IpLookupResponse(true, toDto(existing)))
                .orElseGet(() -> {
                    IpLogDto fromApi = ipRegistryClient.lookupFromExternalApi(ip);
                    IpLog saved = ipLogRepository.save(fromDto(fromApi));
                    return new IpLookupResponse(false, toDto(saved));
                });
    }

    @Transactional(readOnly = true)
    public IpLogsListResponse getIpLogs() {
        List<IpLogDto> list = ipLogRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();

        return new IpLogsListResponse(list.size(), list);
    }

    @Transactional
    public void deleteById(Long id) {
        ipLogRepository.deleteById(id);
    }

    @Transactional
    public void deleteAll() {
        ipLogRepository.deleteAll();
    }


    private IpLogDto toDto(IpLog entity) {
        IpLogDto dto = new IpLogDto();

        dto.setId(entity.getId());
        dto.setType(entity.getType());
        dto.setIp(entity.getIp());
        dto.setCity(entity.getCity());
        dto.setCountry(entity.getCountry());
        dto.setRegion(entity.getRegion());
        dto.setIsp(entity.getIsp());
        dto.setThreatLevel(entity.getThreatLevel());
        dto.setTimeZone(entity.getTimeZone());
        dto.setLatitude(entity.getLatitude());
        dto.setLongitude(entity.getLongitude());
        dto.setCreatedAt(entity.getCreatedAt());

        return dto;
    }

    private IpLog fromDto(IpLogDto dto) {
        IpLog log = new IpLog();

        log.setType(dto.getType());
        log.setIp(dto.getIp());
        log.setCity(dto.getCity());
        log.setCountry(dto.getCountry());
        log.setRegion(dto.getRegion());
        log.setIsp(dto.getIsp());
        log.setThreatLevel(dto.getThreatLevel());
        log.setTimeZone(dto.getTimeZone());
        log.setLatitude(dto.getLatitude());
        log.setLongitude(dto.getLongitude());

        return log;
    }
}

