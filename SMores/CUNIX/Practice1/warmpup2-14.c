//
// Created by David Jefts on 2019-02-14.
//

#include <stdlib.h>
#include <tclDecls.h>

/*
 * "Dynamically create an array of 10 pointers each of which points to a block of 15 integer pointers"
 */
int main() {
    int **ptrptr, i;
    ptrptr = (int **) calloc(10, sizeof(int *));
    for(i = 0; i < 10; i++) {
        ptrptr[i] = (int *) calloc(15, sizeof(int));
    }
    
    /*
     * Free the memory
     */
    for(i = 0; i < 10; i++) {
        free(ptrptr[i]);
    }
    free(ptrptr);
}

int rotate() {
    char x = 0XAC;
    char ans = x & 0X01;
    x = x >> 1;
    ans = ans << 7;
    x = x | ans;
    return x;
}

