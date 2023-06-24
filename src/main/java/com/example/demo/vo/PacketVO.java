package com.example.demo.vo;

import com.example.demo.enums.PacketTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PacketVO {
    // 数据报文ID,数据库中的自增主键
    private Integer id;
    // 同一条以太报文对应同一条serialId
    private Integer serialId;
    // 数据报文的类型
    private PacketTypeEnum type;
    // 数据报文的内容
    private String content;
}
