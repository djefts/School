cmake_minimum_required(VERSION 3.13)
project(Homeworks C)

set(CMAKE_C_STANDARD 11)

include_directories(Homeworks)
include_directories(Practice1)

add_executable(simpleStat Homeworks/Homework4/simpleStat.c)