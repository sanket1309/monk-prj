package com.example.monk_prj.request;

import lombok.Data;

import java.util.Map;

@Data
public class ServiceRequest {
    private Object body;
    private Map<String, String> requestParams;
    private Map<String, String> requestHeaders;
}
