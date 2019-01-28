/*
	Name: David Jefts
	Date Due: 1/24/19
	Short Description: string copy function with null termination to replace the old native strcpy() function
	Grade: 21/20 = 105%
	Comments: Look through the code.
	Also, if you think that there is something wrong with the specs, you should check.
*/
#include <stdio.h>
#include <stdlib.h>

#define STRSIZE 10

int stringcpy(char *dest, char *src, int n);

int main(int argc, char *argv[]) {
    char src[STRSIZE + 1]; //instantiates src at size 10
    int src_size = 0;
    char *dest;
    int n;
    
    printf("Enter a string of at most %d characters (no spaces): ", STRSIZE);
    scanf("%s", src); //does not change the size of src
    //get size of src string
    int i;
	//DGB: Good check
    for(i = 0; i < 10 && src[i] != '\0'; i++) {
        src_size++;  
    }
    
    printf("Enter the number of characters to copy: ");
    scanf("%d", &n);
    
    /* Make EXACT space for the destination string including space for the null character.
       Make sure that n has a positive integer first.
       If not, print an error message and exit the program. THE PDF SAYS TO RETURN 0 NOT EXIT WITH AN ERROR MESSAGE
       Note: You are not supposed to use STRSIZE to create space because you might not need that much.
    */
    
    
    /*
     *
     * AAAAAAAAAAAAAAAAAAAAAAAAAAAAHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH
     * THE INSTRUCTIONS IN THE BLOCK ABOVE DIFFER FROM THE PDF ASSIGNMENT
     * AAAAAAAAAAAAAAAAAAAAAAAAAAAAHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH
     *
     */
    
    //DGB: Very good. +1
    // initial memory allocation
    if(n <= 0) { //make sure n is positive integer
        printf("\nI would recommend using a positive integer for the number of characters to copy.\n\n");
        return 0;
    } else if(n > STRSIZE || n > src_size) {
        printf("Your n is larger than the string you inputted (%d). Defaulted to %d.\n", n, src_size);
        n = src_size;
    }
    dest = (char *) malloc(n+1); //set the size for the dest string
    
    /*********************************************/
    n = stringcpy(dest, src, n); //primary function
    printf("Original: %s\n", src);
    printf("Number of characters copied: %d\n", n);
    printf("Copy: \"%s\"\n", dest);
    return 0;
}/* end main*/

/*
	Copies a string of length n from scr to dest
*/
int stringcpy(char *dest, char *src, int n) {
    
    //loop n times copying from src to dest
    int i, count = 0;
    for(i = 0; i < n && *src != '\0'; i++) {
        *dest = *src;
        dest++;
        src++;
        count++;
    }
    *dest++;  //DGB: Jumped one further because dest was already incremented before exiting loop.
    *dest = '\0'; //write a null character to the last byte
    
    return count;
}
