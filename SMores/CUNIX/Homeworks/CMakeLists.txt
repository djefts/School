cmake_minimum_required(VERSION 3.13)
project(Homeworks C)

set(CMAKE_C_STANDARD 11)

include_directories(.)
include_directories(Practice1)

add_executable(Homeworks
        Practice1/calc.c)
