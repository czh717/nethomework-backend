#include "syshead.h"
#include "ip.h"
#include "sock.h"
#include "utils.h"

// 把socket和data封装成ip报文
struct ip_hdr *ip_send(struct sock *sk,uint8_t data[],uint16_t len){
    struct ip_hdr *ihdr = (struct ip_hdr *)malloc(sizeof(struct ip_hdr)+len);
    memset(ihdr,0,sizeof(struct ip_hdr)+len);

    memcpy(ihdr->data,data,len);

    ihdr->version = IPV4;
    ihdr->ihl = 0x05;
    ihdr->tos = 0;
    ihdr->len = sizeof(struct ip_hdr)+len;
    ihdr->id = ihdr->id;
    ihdr->frag_offset = 0x4000;
    ihdr->ttl = 64;
    ihdr->proto = sk->protocol;
    ihdr->saddr = sk->saddr;
    ihdr->daddr = sk->daddr;
    ihdr->csum = 0;

    ihdr->len = htons(ihdr->len);
    ihdr->id = htons(ihdr->id);
    ihdr->daddr = htonl(ihdr->daddr);
    ihdr->saddr = htonl(ihdr->saddr);
    ihdr->csum = htons(ihdr->csum);
    ihdr->frag_offset = htons(ihdr->frag_offset);
    
    ip_send_check(ihdr);

    return ihdr;
}
// TODO 字节流转换成ip报文
struct ip_hdr *ip_rcv(uint8_t *data){

}

// ip头校验
void ip_send_check(struct ip_hdr *ihdr)
{
    uint32_t csum = checksum(ihdr, ihdr->ihl * 4, 0);
    ihdr->csum = csum;
}