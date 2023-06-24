#include "syshead.h"
#include "utils.h"

// 计算校验和
uint16_t checksum(void *addr, int count, int start_sum)
{
    /* 计算从位置“addr”开始的
     *         字节数的校验和
     * Taken from https://tools.ietf.org/html/rfc1071
     */
    uint32_t sum = start_sum;

    sum += sum_every_16bits(addr, count);
    
    /*  将32位和压缩到16位 */
    while (sum>>16)
        sum = (sum & 0xffff) + (sum >> 16);

    return ~sum;
}

// 每16位求和
uint32_t sum_every_16bits(void *addr, int count)
{
    register uint32_t sum = 0;
    uint16_t * ptr = (uint16_t *)addr;
    
    while( count > 1 )  {
        /*  按位求和 */
        sum += * ptr++;
        count -= 2;
    }

    /*  添加剩余字节 */
    if( count > 0 )
        sum += * (uint8_t *) ptr;

    return sum;
}