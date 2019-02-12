cmake_minimum_required(VERSION 3.13)
project(Homeworks C)

set(CMAKE_C_STANDARD 11)

include_directories(Homeworks)
include_directories(Homeworks/Practice1)

add_executable(Homeworks
        Homeworks/Practice1/calc.c Homeworks/Practice1/SampleExam1Question.c)
