#include "syshead.h"

// TODO 调用虚拟网卡接口发送以太网帧
int tap_send(struct eth_hdr *eth){
    return 0;
}

// TODO 从虚拟网卡接口获取以太网帧数据
struct eth_hdr *tap_rcv(){
    return NULL;
}