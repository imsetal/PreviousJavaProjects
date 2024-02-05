#include "jni.h"
#include <stdio.h>

JNIEXPORT void JNICALL Java_org_example_Main_sayC(JNIEnv *, jobject){
    printf("from C");
}