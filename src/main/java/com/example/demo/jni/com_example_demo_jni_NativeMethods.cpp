#include "com_example_demo_jni_NativeMethods.h"
#include "udp.h"
#include "ip.h"
#include "arp.h"
#include "ethernet.h"
#include "netdev.h"
#include "skbuff.h"
#include "sock.h"
#include "syshead.h"
#include "utils.h"
#include "string.h"

JNIEXPORT jstring JNICALL Java_com_example_demo_jni_NativeMethods_udpSend (JNIEnv *env, jobject ob, jint sPort, jint tPort, jstring sIP, jstring tIP, jstring content) {
	struct *sock = new sock(sPort, tPort, sIP, tIP);
	struct udp_hdr *ret = udp_send(sock, content, content.length);
	string data = "";
	for(int i=0; i<ret->len; i++) {
		data += ret -> data[i];
	}
	return string(ret->sport) + string(ret->dport) + string(ret->len) + string(ret->csum) + data;
}

JNIEXPORT jstring JNICALL Java_com_example_demo_jni_NativeMethods_ipSend
  (JNIEnv *env, jobject ob, jint sPort, jint tPort, jstring sIP, jstring tIP, jstring content) {
  	struct *sock = new sock(sPort, tPort, sIP, tIP);
  	struct ip_hdr *ip = ip_send(sock, content, content.length);
  	string data = "";
  	for(int i=0; i<ret->len; i++) {
  		data += ret -> data[i];
  	}
  	return string(ret->ihl) + string(ret->version) + string(ret->tos) + string(ret->len) + string(ret->id) + string(frag_offset) + string(ttl)
  	 + string(ret->proto) + string(ret->csum) + string(saddr) + string(daddr) + data;
}

JNIEXPORT jstring JNICALL Java_com_example_demo_jni_NativeMethods_getMacAddress
  (JNIEnv *env, jobject ob, jstring ip) {
  	return arp_rcv(new sk_buff());
}

JNIEXPORT jstring JNICALL Java_com_example_demo_jni_NativeMethods_ethSend
  (JNIEnv *env, jobject ob, jstring smac, jstring dmac, jstring content){
  	struct eth_hdr* ret = eht_send(smac, dmac, content.length, content, content.length);
  	string data = "";
  	for(int i=0; i<ret->ethertype; i++) {
  		data += ret -> data[i];
  	}
  	return string(ret->dmac) + string(ret->smac) + string(ret->ethertype) + data;
}

JNIEXPORT jstring JNICALL Java_com_example_demo_jni_NativeMethods_tapSend
  (JNIEnv *env, jobject pb, jstring content){
  	netdev_transmit(content);
}

JNIEXPORT jstring JNICALL Java_com_example_demo_jni_NativeMethods_tapRcv
  (JNIEnv *env, jobject ob) {
  	return netdev_rx_loop();
};

JNIEXPORT jstring JNICALL Java_com_example_demo_jni_NativeMethods_ethRcv
  (JNIEnv *env, jobject ob, jstring content) {
  	struct eth_hdr* ret = eht_rcv(content);
  	string data = "";
  	for(int i=0; i<ret->ethertype; i++) {
  		data += ret -> data[i];
  	}
  	return string(ret->dmac) + string(ret->smac) + string(ret->ethertype) + data;
}

JNIEXPORT jstring JNICALL Java_com_example_demo_jni_NativeMethods_ipRcv
  (JNIEnv *env, jobject ob, jstring content) {
  	struct ip_hdr *ret = ip_rcv(content);
  	string data = "";
  	for(int i=0; i<ret->len; i++) {
  		data += ret -> data[i];
  	}
  	return string(ret->ihl) + string(ret->version) + string(ret->tos) + string(ret->len) + string(ret->id) + string(frag_offset) + string(ttl)
  	 + string(ret->proto) + string(ret->csum) + string(saddr) + string(daddr) + data;
}

JNIEXPORT jstring JNICALL Java_com_example_demo_jni_NativeMethods_udpRcv
  (JNIEnv *env, jobject ob, jstring content) {
  	struct udp_hdr *ret = udp_rcv(content);
	string data = "";
	for(int i=0; i<ret->len; i++) {
		data += ret -> data[i];
	}
	return string(ret->sport) + string(ret->dport) + string(ret->len) + string(ret->csum) + data;
};

