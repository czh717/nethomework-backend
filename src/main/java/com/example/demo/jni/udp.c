#include "sock.h"
#include "udp.h"
#include "utils.h"

// 把socket和data封装成udp报文
struct udp_hdr *udp_send(struct sock *sk,uint8_t data[],uint16_t len){
    struct udp_hdr *udp = (struct udp_hdr *)malloc(sizeof(struct udp_hdr)+len);
    memset(udp,0,sizeof(struct udp_hdr)+len);

    memcpy(udp->data,data,len);

    udp->sport = htons(sk->sport);
    udp->dport = htons(sk->dport);
    udp->len = htons(sizeof(struct udp_hdr)+len);
    udp->csum = tcp_udp_checksum(htonl(sk->saddr),htonl(sk->daddr),IP_UDP,data,len);
    return udp;
}



// TODO 字节流转换成udp报文
struct udp_hdr *udp_rcv(uint8_t *data){
    return NULL;
}

// tcp_udp校验
int tcp_udp_checksum(uint32_t saddr, uint32_t daddr, uint8_t proto,
                     uint8_t *data, uint16_t len)
{
    uint32_t sum = 0;

    sum += saddr;
    sum += daddr;
    sum += htons(proto);
    sum += htons(len);
    
    return checksum(data, len, sum);
}

