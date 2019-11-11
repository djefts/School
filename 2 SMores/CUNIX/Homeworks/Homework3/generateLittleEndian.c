#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <errno.h>
#include <limits.h> /* for INT_MAX */

#define NUM_INTS 64

int main(int argc, char **argv) {
    FILE *outfile, *infile;
    int integers[NUM_INTS];
    unsigned int i;
    unsigned char ptr;
    
    if((outfile = fopen("littleEndian.bin", "wb")) == NULL) {
        perror("Error opening file for writing");
        exit(EXIT_FAILURE);
    }
    
    for(i = 0; i < NUM_INTS; i++) {
        integers[i] = (rand() * INT_MAX);
        printf("%08x ", integers[i]);
        if((i + 1) % 8 == 0)
            printf("\n");
    }
    
    printf("\n");
    if(fwrite(integers, sizeof(integers), 1, outfile) != 1) {
        perror("Error writing to file");
        exit(EXIT_FAILURE);
    }
    
    fclose(outfile);
    
    /* Read back file byte at a time to see the endian-ness */
    if((infile = fopen("littleEndian.bin", "rb")) == NULL) {
        perror("Error opening file for reading");
        exit(EXIT_FAILURE);
    }
    
    /*
     * PRINT OUT THE FILE
     */
    printf("\nReading back the file one byte at a time.\n");
    for(i = 0; i < NUM_INTS * sizeof(int); i++) {
        if(fread(&ptr, sizeof(char), 1, infile) != 1) {
            perror("Error reading from file");
            exit(EXIT_FAILURE);
        }
        printf("%02x", ptr);
        if((i + 1) % 32 == 0)
            printf("\n");
        else if((i + 1) % sizeof(int) == 0)
            printf(" ");
    }
    
    printf("\n");
    
    fclose(infile);
    
    return 0;
}

