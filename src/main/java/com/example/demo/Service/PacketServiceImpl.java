package com.example.demo.Service;

import com.example.demo.mapper.PacketMapper;
import com.example.demo.vo.PacketVO;
import com.example.demo.vo.RequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacketServiceImpl implements PacketService{
    @Autowired
    private PacketMapper packetMapper;

    @Override
    public void send(RequestVO vo) {

    }

    @Override
    public List<PacketVO> get() {
        return packetMapper.selectPacket();
    }

    @Override
    public List<PacketVO> getPacketBySerialId(int serialId) {
        return packetMapper.selectPacketBySerialId(serialId);
    }

    @Override
    public List<Integer> getAllSerialId() {
        return packetMapper.getAllSerialId();
    }
}
