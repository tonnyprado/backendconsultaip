package com.example.backendConsultaIP.dto;

import lombok.Data;

@Data
public class IpLookupResponse {
    private boolean alreadyExists;
    private IpLogDto record;

    public IpLookupResponse(boolean alreadyExists, IpLogDto record) {
        this.alreadyExists = alreadyExists;
        this.record = record;
    }
}

