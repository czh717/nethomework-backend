package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestVO {
    private String sourceIP;
    private int sourcePort;
    private String targetIP;
    private int targetPort;
    private String msg;
}
