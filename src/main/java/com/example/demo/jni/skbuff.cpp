#include "skbuff.h"
// 取sk_buff首部地址
uint8_t *skb_head(struct sk_buff *skb)
{
    return skb->head;
}