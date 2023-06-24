package com.example.demo.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum PacketTypeEnum {
    @JsonProperty("Ethernet")
    Ethernet("Ethernet"),
    @JsonProperty("MAC")
    MAC("MAC"),
    @JsonProperty("IP")
    IP("IP"),
    @JsonProperty("UDP")
    UDP("UDP");

    private final String value;
    PacketTypeEnum(String value){
        this.value = value;
    }

    @Override
    public String toString(){
        return value;
    }
}
