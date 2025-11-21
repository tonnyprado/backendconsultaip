package com.example.backendConsultaIP.controller;

import com.example.backendConsultaIP.dto.*;
import com.example.backendConsultaIP.service.IpLookupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class IpLogController {

    private final IpLookupService service;

    @PostMapping("/ip-lookup")
    public ResponseEntity<IpLookupResponse> lookup(@RequestBody IpLookupRequest request) {
        IpLookupResponse response = service.lookupIp(request.ip());
        HttpStatus status = response.isAlreadyExists() ? HttpStatus.OK : HttpStatus.CREATED;
        return new ResponseEntity<>(response, status);
    }

    @GetMapping("/ip-logs")
    public IpLogsListResponse getLogs() {
        return service.getIpLogs();
    }

    @DeleteMapping("/ip-logs/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/ip-logs")
    public ResponseEntity<Void> deleteAll() {
        service.deleteAll();
        return ResponseEntity.noContent().build();
    }
}

