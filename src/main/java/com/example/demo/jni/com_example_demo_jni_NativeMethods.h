/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_example_demo_jni_NativeMethods */

#ifndef _Included_com_example_demo_jni_NativeMethods
#define _Included_com_example_demo_jni_NativeMethods
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_example_demo_jni_NativeMethods
 * Method:    udpSend
 * Signature: (IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_example_demo_jni_NativeMethods_udpSend
  (JNIEnv *, jobject, jint, jint, jstring, jstring, jstring);

/*
 * Class:     com_example_demo_jni_NativeMethods
 * Method:    ipSend
 * Signature: (IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_example_demo_jni_NativeMethods_ipSend
  (JNIEnv *, jobject, jint, jint, jstring, jstring, jstring);

/*
 * Class:     com_example_demo_jni_NativeMethods
 * Method:    getMacAddress
 * Signature: (Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_example_demo_jni_NativeMethods_getMacAddress
  (JNIEnv *, jobject, jstring);

/*
 * Class:     com_example_demo_jni_NativeMethods
 * Method:    ethSend
 * Signature: (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_example_demo_jni_NativeMethods_ethSend
  (JNIEnv *, jobject, jstring, jstring, jstring);

/*
 * Class:     com_example_demo_jni_NativeMethods
 * Method:    tapSend
 * Signature: (Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_example_demo_jni_NativeMethods_tapSend
  (JNIEnv *, jobject, jstring);

/*
 * Class:     com_example_demo_jni_NativeMethods
 * Method:    tapRcv
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_example_demo_jni_NativeMethods_tapRcv
  (JNIEnv *, jobject);

/*
 * Class:     com_example_demo_jni_NativeMethods
 * Method:    ethRcv
 * Signature: (Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_example_demo_jni_NativeMethods_ethRcv
  (JNIEnv *, jobject, jstring);

/*
 * Class:     com_example_demo_jni_NativeMethods
 * Method:    ipRcv
 * Signature: (Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_example_demo_jni_NativeMethods_ipRcv
  (JNIEnv *, jobject, jstring);

/*
 * Class:     com_example_demo_jni_NativeMethods
 * Method:    udpRcv
 * Signature: (Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_example_demo_jni_NativeMethods_udpRcv
  (JNIEnv *, jobject, jstring);

#ifdef __cplusplus
}
#endif
#endif
