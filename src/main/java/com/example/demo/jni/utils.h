#include "syshead.h"

uint16_t checksum(void *addr, int count, int start_sum);
uint32_t sum_every_16bits(void *addr, int count);