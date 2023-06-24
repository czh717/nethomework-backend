package com.example.demo.mapper;

import com.example.demo.vo.PacketVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacketMapper {
    @InsertProvider(type = GeneralInsertUpdateSqlProvider.class, method = "insert")
    void insertPacket(PacketVO packetVO);

    @Select("select * from packet")
    List<PacketVO> selectPacket();

    @Select("select * from packet where serialId = #{serialId}")
    List<PacketVO> selectPacketBySerialId(int serialId);

    @Select("select serialId from packet group by serialId")
    List<Integer> getAllSerialId();
}
