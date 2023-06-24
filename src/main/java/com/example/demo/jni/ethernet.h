#include "syshead.h"
#include "skbuff.h"
#define ETH_HDR_LEN sizeof(struct eth_hdr)

struct eth_hdr
{
    uint8_t  dmac[6];   // 目的地址
    uint8_t  smac[6];   // 源地址
    uint16_t ethertype; // 有效负载的长度(隐式类型标定,字段值大于1536则包含有效负载的类型信息，小于则包含有效负载的长度)
    uint8_t  payload[];
};


struct eth_hdr *eth_send(uint8_t smac[6],uint8_t dmac[6],uint16_t ethertype,uint8_t data[],uint16_t len);
