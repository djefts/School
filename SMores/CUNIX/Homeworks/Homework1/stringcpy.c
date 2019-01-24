/*
	Name: David Jefts
	Date Due: 1/24/19
	Short Description: string copy function with null termination to replace the old native strcpy() function
*/
#include <stdio.h>
#include <stdlib.h>

#define STRSIZE 10

int stringcpy(char *dest, char *src, int n);


int main(int argc, char *argv[]) {
    char src[STRSIZE + 1];
    char *dest;
    int n;

    printf("Enter a string of at most %d characters: ", STRSIZE);
    scanf("%s", src);

    printf("Enter the number of characters to copy: ");
    scanf("%d", &n);

    /* Make EXACT space for the destination string including space for the null character.
       Make sure that n has a positive integer first.
       If not, print an error message and exit the program.
       Note: You are not supposed to use STRSIZE to create space because you might not need that much.
    */

    // Make sure n is a positive integer
    if (n <= 0) {
        printf("Error: number of characters to copy must be a positive integer.");
        exit(1);
    }

    // make exact space for dest
    *dest = malloc(n+1);

    /*********************************************/
    n = stringcpy(dest, src, n);
    printf("Original: %s\n", src);
    printf("Number of characters copied: %d\n", n);
    printf("Copy: %s\n", dest);
    return 0;
}/* end main*/

/*
	Copies a string of length n from scr to dest
*/
int stringcpy(char *dest, char *src, int n) {

    int i;
    for (i = 0; i < n-1 && src != '\0'; i++) {
        dest[i] = src[i];
    }
    dest[i] = '\0';

    return *dest;
}
