#include <jni.h>
#include <iostream>
#include "org_example_HelloWorld.h"
 
JNIEXPORT void JNICALL Java_HelloWorld_printHelloWorld(JNIEnv* env, jobject) {
    std::cout << "Hello World from c++!" << std::endl;
}
 
JNIEXPORT void JNICALL Java_HelloWorld_say(JNIEnv* env, jobject, jstring str) {
    const char *nativeString = env->GetStringUTFChars(str, 0);
    std::cout << nativeString << std::endl;
    env->ReleaseStringUTFChars(str, nativeString);
}