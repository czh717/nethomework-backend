package com.example.demo.Service;

import com.example.demo.enums.PacketTypeEnum;
import com.example.demo.jni.NativeMethods;
import com.example.demo.mapper.PacketMapper;
import com.example.demo.vo.PacketVO;
import com.example.demo.vo.RequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacketServiceImpl implements PacketService{
    @Autowired
    private PacketMapper packetMapper;
    private NativeMethods nativeMethods = new NativeMethods();

    @Override
    public void send(RequestVO vo) {
        int serialId = getAllSerialId().size() + 1;
        // UDP数据封装
        String udpData = nativeMethods.udpSend(vo.getSourcePort(),vo.getTargetPort(),vo.getSourceIP(), vo.getTargetIP(), vo.getMsg());
        PacketVO packetVO = new PacketVO();
        packetVO.setSerialId(serialId);
        packetVO.setType(PacketTypeEnum.UDP);
        packetVO.setContent(udpData);
        packetMapper.insertPacket(packetVO);

        //IP数据封装
        String ipData = nativeMethods.ipSend(vo.getSourcePort(),vo.getTargetPort(),vo.getSourceIP(), vo.getTargetIP(), udpData);
        packetVO.setType(PacketTypeEnum.IP);
        packetVO.setContent(ipData);
        packetMapper.insertPacket(packetVO);

        //获取Mac地址
        String sMacAddress = nativeMethods.getMacAddress(vo.getSourceIP());
        String tMacAddress = nativeMethods.getMacAddress(vo.getTargetIP());
        String macData = nativeMethods.ethSend(sMacAddress, tMacAddress, ipData);
        packetVO.setType(PacketTypeEnum.MAC);
        packetVO.setContent(macData);
        packetMapper.insertPacket(packetVO);

        //调用虚拟网卡
        String tapData = nativeMethods.tapSend(macData);
//        packetVO.setType(PacketTypeEnum.Tap);
//        packetVO.setContent(tapData);
//        packetMapper.insertPacket(packetVO);
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

    @Override
    @Scheduled(cron = "* */1 * * * ?")
    // 每分钟执行一次，获取网卡数据
    public void receive() {
        PacketVO packetVO = new PacketVO();
        packetVO.setSerialId(getAllSerialId().size() + 1);
        // 网卡接收Tap数据
        String tapData = nativeMethods.tapRcv();
//        packetVO.setType(PacketTypeEnum.Tap);
//        packetVO.setContent(tapData);
//        packetMapper.insertPacket(packetVO);

        // 解析为Mac数据
        String macData = nativeMethods.ethRcv(tapData);
        packetVO.setType(PacketTypeEnum.MAC);
        packetVO.setContent(macData);
        packetMapper.insertPacket(packetVO);

        // 解析为Ip数据报文
        String ipData = nativeMethods.ipRcv(macData);
        packetVO.setType(PacketTypeEnum.IP);
        packetVO.setContent(ipData);
        packetMapper.insertPacket(packetVO);

        // 解析为UDP数据
        String udpData = nativeMethods.udpRcv(ipData);
        packetVO.setType(PacketTypeEnum.UDP);
        packetVO.setContent(udpData);
        packetMapper.insertPacket(packetVO);
    }
}
