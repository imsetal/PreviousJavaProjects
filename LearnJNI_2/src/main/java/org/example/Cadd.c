#include "jni.h"

JNIEXPORT jint JNICALL Java_org_example_Main_add(JNIEnv *, jobject, jint a, jint b){
    return a+b;
}

