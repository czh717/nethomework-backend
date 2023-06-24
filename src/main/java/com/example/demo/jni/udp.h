#include <stdint.h>
#include "ip.h"

#define IP_TCP 0x06
#define IP_UDP 0x11

struct udp_hdr;

struct udp_hdr{
    // 源端口
    uint16_t sport;
    // 目的端口
    uint16_t dport;
    // 长度
    uint16_t len;
    // 检验和
    uint16_t csum;
    // udp数据部分
    uint8_t data[];
};

struct udp_hdr *udp_send(struct sock *sk,uint8_t data[],uint16_t len);
int tcp_udp_checksum(uint32_t saddr, uint32_t daddr, uint8_t proto,uint8_t *data, uint16_t len);
