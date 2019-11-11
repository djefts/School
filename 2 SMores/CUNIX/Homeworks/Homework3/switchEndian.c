/*
	Author: David Jefts
	Date: 02/16/19
	Program: stringjoinv2.c
	Description:
		The program reads a series of little endian numbers from a binary file, converts them to big endian, then
		writes them to the file bigEndian.bin
*/

#include <stdlib.h>
#include <stdio.h>
#include <stdint.h>
#include <string.h>

void printFile(char *, long);

long fileSize(FILE *);

uint32_t swapInt(uint32_t);

int main() {
    printf("Starting program\n\n");
    //open file
    FILE *infile;
    infile = fopen("littleEndian.bin", "rb");  // r for read, b for binary
    
    //get file size and set buffer length
    int intSize = sizeof(uint32_t);
    long buffer_size = fileSize(infile);
    unsigned char buffer[buffer_size];
    uint32_t numbers[buffer_size / 4];
    int i = 0, j = 0, num;
    
    //print the original file (formatted)
    printf("Original file (%li bytes):", buffer_size);
    printFile("littleEndian.bin", buffer_size);
    
    //get all of the numbers into an array
    printf("\nFormatting all of the numbers\n");
    unsigned char ptr;
    for(i = 0; i < buffer_size; i += intSize) {
        if(fread(&ptr, intSize, 1, infile) != 1) {
            perror("Error reading from file");
            exit(EXIT_FAILURE);
        }
        printf("%x ", ptr);
        buffer[i] = ptr;
    }
    for(num = 0; num < sizeof(buffer); num++) {
        printf("%X ", buffer[num]);
    }
    
    //switch each number
    printf("\nSwitching all of the numbers\n");
    for(i = 0; i < sizeof(buffer); i++) {
        numbers[i] = swapInt((uint32_t) buffer[i]);
    }
    
    //write all the numbers to the file
    printf("\nWriting all of the numbers\n");
    FILE *write_ptr;
    write_ptr = fopen("bigEndian.bin", "wb");  // w for write, b for binary
    for(num =  0; num< sizeof(numbers); num++) {
        fwrite(numbers, sizeof(numbers), 1, write_ptr);
    }
    printf("\n\n");
}

void printFile(char *file_name, long size) {
    FILE *infile;
    infile = fopen(file_name, "rb");
    int i;
    unsigned char ptr;
    
    printf("\nReading back the file one byte at a time.\n");
    for(i = 0; i < size; i++) {
        if(fread(&ptr, 1, 1, infile) != 1) {
            perror("Error reading from file");
            exit(EXIT_FAILURE);
        }
        printf("%02x", ptr);
        if((i + 1) % 32 == 0)
            printf("\n");
        else if((i + 1) % sizeof(int) == 0)
            printf(" ");
    }
    rewind(infile);
}

long fileSize(FILE *file) {
    long size;
    fseek(file, 0L, SEEK_END);
    size = ftell(file);
    rewind(file);
    return size;
}

uint32_t swapInt(uint32_t num) {
    return ((num >> 24) & 0xff) | // move byte 3 to byte 0
           ((num << 8) & 0xff0000) | // move byte 1 to byte 2
           ((num >> 8) & 0xff00) | // move byte 2 to byte 1
           ((num << 24) & 0xff000000); // byte 0 to byte 3
}