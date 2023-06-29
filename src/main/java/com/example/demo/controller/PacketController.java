package com.example.demo.controller;

import com.example.demo.Service.PacketService;
import com.example.demo.vo.RequestVO;
import com.example.demo.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/packet")
public class PacketController {
    @Autowired
    private PacketService packetService;

    @PostMapping("/send")
    public Response send(@RequestBody RequestVO requestVO) {
        packetService.send(requestVO);
        return Response.buildSuccess();
    }

    @GetMapping("/get")
    public Response get() {
        return Response.buildSuccess(packetService.get());
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
