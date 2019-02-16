//
// Created by David Jefts on 2019-02-14.
//

#include <stdlib.h>
#include <stdio.h>

int main() {
    printf("Starting program\n");
    //open the file
    unsigned char buffer[10];
    FILE *infile;
    
    printf("Created variables\n");
    
    infile = fopen("littleEndian.bin", "rb");  // r for read, b for binary
    
    fread(buffer, sizeof(char), 1, infile);
    
    printf("buffer: %s\n", buffer);
    
    printf("\n\n");
}
