#include "syshead.h"
#include "ethernet.h"

// 把MAC地址和data封装成MAC数据帧
struct eth_hdr *eth_send(uint8_t smac[6],uint8_t dmac[6],uint16_t ethertype,uint8_t data[],uint16_t len){
    struct eth_hdr *eth = (struct eth_hdr *)malloc(sizeof(struct eth_hdr)+len);
    memset(eth,0,sizeof(struct eth_hdr)+len);

    memcpy(eth->payload,data,len);
    memcpy(eth->smac,smac,6);
    memcpy(eth->dmac,dmac,6);
    eth->ethertype = ethertype;
    
    return eth;
}

// 字节流转换成MAC数据帧
struct eth_hdr* eth_rcv(uint8_t *data){
    struct eth_hdr* hdr =(struct eth_hdr *) data;
    return hdr;
}