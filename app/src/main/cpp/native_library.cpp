#include <jni.h>
#include <string>
#include <iostream>
#include <utility>

using namespace std;

string jstring2string(JNIEnv *env, jstring jStr) {
    if (!jStr)
        return "";

    const jclass stringClass = env->GetObjectClass(jStr);
    const jmethodID getBytes = env->GetMethodID(stringClass, "getBytes", "(Ljava/lang/String;)[B");
    const auto stringJbytes = (jbyteArray) env->CallObjectMethod(jStr, getBytes,
                                                                 env->NewStringUTF("UTF-8"));

    auto length = (size_t) env->GetArrayLength(stringJbytes);
    jbyte *pBytes = env->GetByteArrayElements(stringJbytes, nullptr);

    std::string ret = std::string((char *) pBytes, length);
    env->ReleaseByteArrayElements(stringJbytes, pBytes, JNI_ABORT);

    env->DeleteLocalRef(stringJbytes);
    env->DeleteLocalRef(stringClass);
    return ret;
}

string hardcoreEncryption(JNIEnv *env, jstring text) {
    // here is the plain text password, just in case...
    string p = "supersecret";
    char k = 'K';
    string pass = jstring2string(env, text);
    string output = pass;

    for (int i = 0; i < pass.size(); i++)
        output[i] = pass[i] ^ k;

    return output;
}

jboolean checkPass(JNIEnv *env, jstring pass) {
    return hardcoreEncryption(env, pass) == "8>;.98.(9.?";
}

extern "C"
JNIEXPORT jboolean JNICALL
Java_infosecadventures_allsafe_challenges_NativeLibrary_checkPassword(JNIEnv *env, jobject thiz,
                                                                      jstring password) {
    return checkPass(env, password);
}