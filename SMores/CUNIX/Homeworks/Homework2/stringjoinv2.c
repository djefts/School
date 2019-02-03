/*
	Author:
	Date:
	Program: stringjoinv2.c
	Description:
		The program accepts a variable number of strings from the user.
		Then it accepts a string (possibly empty) that will be used to join the strings.
		Then it calls a function that will create a string made up of the entered strings
		and the delimiter string.
		Then it calls a function to display the individual strings, the delimiter, and the 
		joined string.
		
	Note from DGB: This version replaces most of the dereferencing with indexes. This is equivalent
	to dereferencing.
	It also uses calloc instead of malloc and realloc instead of the logic for creating a bigger array.
	It also frees up all memory requested after it is finished using that memory.
*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*Defined constants */
#define STRSIZE 10 /* does not include space for null character */

/* Function prototypes */
char ** getStringsFromUser();
void display(char **, char *, char *);
void initPtrArray(char **, int);
char * joinstring(char **, char *);
void freemem(char **);

int main(int argc, char * argv[])
{
	char ** strptrptr = 0;
	char * joinedString = 0;
	char delimiter[3] = {'\0', '\0', '\0'};
	
	strptrptr = getStringsFromUser();
	if(strptrptr[0] == 0)
	{
		printf("No strings entered. Bye!\n");
		return 0;
	}
	printf("Enter the joining string: ");
	scanf("%2s", delimiter);
	joinedString = joinstring(strptrptr, delimiter);
	display(strptrptr, delimiter, "");  /* you need to replace "" with the variable joinedString 
	once you get the function joinstring to work */
	freemem(strptrptr); /* give back all memory to the system. */
	/* free the array of pointers. Note that this is done after freeing all the strings. */
	free(strptrptr);
	strptrptr = 0; /*You must do this because strptrptr still has the address of the array which
					is now unusable. */
	return 0;
}/*end main*/

char ** getStringsFromUser()
{
	char answer, junk;
	/* start off with 3 locations for pointers*/
	char ** originalArrayPtr = (char **) calloc(3, sizeof(char *)); 
	int count = 3;  /* keeps track of the amount of space currently allocated to array */
	int i = 0; /* keeps track of the next free location in the array */
	
	/* Ask user if (s)he wants to enter a string */
	printf("Do you want to enter a string? (y/n)");
	scanf("%c", &answer);
	
	while(answer == 'y' || answer == 'Y')
	{
		/* check if you have room. First time you do. */
		if(i == count-1) /* no room so make some */
		{
			count = i * 2 + 1;  /* extra 1 is for the null */
			/* The following makes more room and copies all in one.
			You need to assign it back to the original in case the entire block
			was moved in memory. */
			originalArrayPtr = (char **) realloc(originalArrayPtr, count * sizeof(char *));
		}
		char * str = (char *) calloc((STRSIZE + 1), sizeof(char));
		printf("Enter your string: ");
		scanf("%s", str);
		scanf("%c", &junk); /* eat up return left in the buffer */
		
		/* assign the string to the currently free location in the array */
		originalArrayPtr[i] = str;
		i++;
		/* Ask user again */
		printf("Do you want to enter a string? (y/n)");
		scanf("%c", &answer);
	}/*end main while loop*/
	/* make sure that there is a null in the position after the last string */
	originalArrayPtr[i] = 0;
	return originalArrayPtr;
}/*end getStringsFromUser*/

void initPtrArray(char ** ptr2ptrs, int n)
{
	int i = 0;
	for(;i < n; i++)
		ptr2ptrs[i] = 0;
}/* end initPtrArray */


/********************************************************
Fill in the code for the following function.
You can use ONE string function, strlen, to help you find the length of a string.
For any other string manipulations, write your own functions.
********************************************************/

char * joinstring(char ** stringPtrs, char * delimiter)
{
	char * joinedStr = 0;
	
	
	
	return joinedStr;
}

void display(char ** stringPtrs, char * delimiter, char * joinedStr)
{
	printf("Original strings\n");
	/* This was straightforward so I left it alone. */
	while(*stringPtrs)
	{
		printf("%s\n", *stringPtrs);
		stringPtrs++;
	}
	
	printf("Delimiter: %s\n", delimiter);
	printf("Joined string: %s\n", joinedStr);
	
}/*end display*/

void freemem(char ** mem)
{
	int i = 0;
	while(mem[i])
	{
		free(mem[i]);  /* free space for each string */
		mem[i++] = 0; /* zero out the pointer and increment i*/
	}
}