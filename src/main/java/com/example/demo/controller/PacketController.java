package com.example.demo.controller;

import com.example.demo.Service.PacketService;
import com.example.demo.vo.PacketVO;
import com.example.demo.vo.RequestVO;
import com.example.demo.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/packet")
public class PacketController {
    @Autowired
    private PacketService packetService;

    @PostMapping("/send")
    public Response send(@RequestParam("send_ip_addr") String sendIp, @RequestParam("send_port") String sendPort,
                         @RequestParam("receive_ip_addr") String receiveIp, @RequestParam("receive_port") String receivePort,
                         @RequestParam("message_data") String data) {
        packetService.send(new RequestVO(sendIp, Integer.valueOf(sendPort), receiveIp, Integer.valueOf(receivePort), data));
        return Response.buildSuccess();
    }

    @GetMapping("/get")
    public Response get() {
        List<PacketVO>  packetVOList = packetService.get();
        return Response.buildSuccess(packetVOList.stream().map(vo -> vo.getContent()).collect(Collectors.toList()));
    }

    @GetMapping("/get/{serialId}")
    public Response getBySerialId(@PathVariable("serialId") int serialId) {
        return Response.buildSuccess(packetService.getPacketBySerialId(serialId));
    }

    @GetMapping("/getSerialId")
    public Response getSerialId() {
        return Response.buildSuccess(packetService.getAllSerialId());
    }

}
