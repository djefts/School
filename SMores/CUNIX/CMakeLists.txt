cmake_minimum_required(VERSION 3.13)
project(Homeworks C)

set(CMAKE_C_STANDARD 11)

include_directories(Homeworks)
include_directories(Practice1)

add_executable(Homeworks
        Practice1/calc.c Practice1/SampleExam1Question.c Practice1/warmpup2-14.c "Homeworks/Homework 3/switchEndian.c")
