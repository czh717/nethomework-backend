#include <iostream>
#include "sock.h"
#include "udp.h"


int main(){
    char str[10] = "123456789";
    struct sock sock; 
    sock.saddr = 0x11111111;
    sock.daddr = 0x22222222;
    sock.sport = 0x1000;
    sock.dport = 0x2000;
    sock.protocol = 0;
    uint8_t data[200] = "123";
    struct udp_hdr *udp = udp_send(&sock,data,200);
    struct ip_hdr *ip = ip_send(&sock,(uint8_t *)udp,udp->len);

    uint8_t smac[6];
    smac[0]=0x00;
    smac[1]=0x11;
    smac[2]=0x22;
    smac[3]=0x33;
    smac[4]=0x44;
    smac[5]=0x55;

    uint8_t dmac[6];
    dmac[0]=0x66;
    dmac[1]=0x77;
    dmac[2]=0x88;
    dmac[3]=0x99;
    dmac[4]=0xaa;
    dmac[5]=0xbb;

    uint16_t ethertype = 0x11;
    struct eth_hdr *eth = eth_send(smac,dmac,ethertype,(uint8_t *)ip,ip->len);


}