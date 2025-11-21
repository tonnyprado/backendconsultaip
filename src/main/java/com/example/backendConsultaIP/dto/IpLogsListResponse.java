package com.example.backendConsultaIP.dto;

import lombok.Data;
import java.util.List;

@Data
public class IpLogsListResponse {
    private long total;
    private List<IpLogDto> records;

    public IpLogsListResponse(long total, List<IpLogDto> records) {
        this.total = total;
        this.records = records;
    }
}

