//
// Created by David Jefts on 2019-02-05.
//

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char *argv[]) {
    char p[] = {12, -5, 0, 7};
    char *q;
    int i;
    q = p;
    q += 2;
    (*q)++;
    q = &p[1];
    *q += 10;
    p[0] %= p[3];
    printf("%i\n", (int) sizeof(p));
    for(int i = 0; i < sizeof(p); i++) {
        printf("%d ", p[i]);
    }
    printf("\n");
}