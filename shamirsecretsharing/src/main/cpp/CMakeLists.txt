cmake_minimum_required(VERSION 3.4.1)
project (sss-jni)

add_library(sss-jni SHARED
            sss-jni.c
            sss/sss.c
            sss/hazmat.c
            sss/randombytes.c
            sss/tweetnacl.c)

# Include libraries needed for sss-jni lib
target_link_libraries(sss-jni
                      android)
