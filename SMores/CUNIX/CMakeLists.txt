cmake_minimum_required(VERSION 3.13)
project(Homeworks C)

set(CMAKE_C_STANDARD 11)

include_directories(Homeworks)
include_directories(Practice1)

add_executable(simpleStat Homeworks/Homework4/simpleStat.c)
add_executable(doublyLinkedList Homeworks/Homework5/doublyLinkedList.c)
add_executable(serverWithMenu "Final Demos/ClientServerWithMenuExample/Server/serverWithMenu.c")
add_executable(clientWithMenu "Final Demos/ClientServerWithMenuExample/Client/clientWithMenu.c")