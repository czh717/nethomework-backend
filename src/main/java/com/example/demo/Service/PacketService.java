package com.example.demo.Service;

import com.example.demo.vo.PacketVO;
import com.example.demo.vo.RequestVO;

import java.util.List;

public interface PacketService {
    public void send(RequestVO vo);

    List<PacketVO> get();

    List<PacketVO> getPacketBySerialId(int serialId);

    List<Integer> getAllSerialId();
}
