/*
	Name: David Jefts
	Date Due: 1/24/19
	Short Description: 
*/
#include <stdio.h>
#include <stdlib.h>
#define STRSIZE 10

int stringcpy(char * dest, char * src, int n);


int main(int argc, char * argv[])
{
	char src[STRSIZE+1];
	char * dest;
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
	
	

	/*********************************************/
	n = stringcpy(dest, src, n);
	printf("Original: %s\n", src);
	printf("Number of characters copied: %d\n", n);
	printf("Copy: %s\n", dest);
  return 0;
}/* end main*/

/*
	Description of stringcpy:
*/
/*Definition of stringcpy */
