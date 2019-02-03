/*
	Author: David Jefts
	Date: 02/02/2019
	Program: stringjoin.c
	Description:
		The program accepts a variable number of strings from the user.
		Then it accepts a string (possibly empty) that will be used to join the strings.
		Then it calls a function that will create a string made up of the entered strings
		and the delimiter string.
		Then it calls a function to display the individual strings, the delimiter, and the 
		joined string.
		
	Note from DGB: This can be solved in many ways especially more efficiently.
	I am solving it with the tools that you knew how to use on Thursday, 1/24/2019.
	I made it modular so as to keep the main function clean. This wasn't an explicit requirement
	but this is how you should be automatically thinking when writing code.
*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*Defined constants */
#define STRSIZE 10 /* does not include space for null character */

/* Function prototypes */
char **getStringsFromUser();

void display(char **, char *, char *);

void initPtrArray(char **, int);

char *joinstring(char **, char *);

void freemem(char **);

int main(int argc, char *argv[]) {
    char **strptrptr = 0;
    char *joinedString = 0;
    char delimiter[3] = {'\0', '\0', '\0'};
    int i;
    
    strptrptr = getStringsFromUser();
    if(*strptrptr == 0) {
        printf("No strings entered. Bye!\n");
        return 0;
    }
    printf("Enter the joining string: ");
    scanf("%2s", delimiter);
    joinedString = joinstring(strptrptr, delimiter);
    /* In the next line you need to replace "" with the variable joinedString
	once you get the function joinstring to work */
    display(strptrptr, delimiter, joinedString);
    
    freemem(strptrptr); /* give back all memory to the system. */
    /* free the array of pointers. Note that this is done after freeing all the strings. */
    free(strptrptr);
    strptrptr = 0; /*You must do this because strptrptr still has the address of the array which
					is now unusable. */
}/*end main*/

char **getStringsFromUser() {
    char answer, junk;
    /* start off with 3 locations for pointers*/
    char **originalArrayPtr = (char **) malloc(sizeof(char *) * 3);
    int count = 3;  /* keeps track of the amount of space currently allocated to array */
    int i = 0; /* keeps track of the next free location in the array */
    
    /* fill with nulls */
    initPtrArray(originalArrayPtr,
                 3); /* I could have save myself a lot of trouble here by using calloc instead of malloc above.*/
    
    /* Ask user if (s)he wants to enter a string */
    printf("Do you want to enter a string? (y/n)");
    scanf("%c", &answer);
    
    while(answer == 'y' || answer == 'Y') {
        /* check if you have room. First time you do. */
        if(i == count - 1) /* no room so make some */
        {
            count = i * 2 + 1;  /* extra 1 is for the null */
            char **biggerArrayPtr = (char **) malloc(sizeof(char *) * count);
            int j = 0;
            initPtrArray(biggerArrayPtr, count);
            /* copy what was in the original array to the new array.*/
            for(; j < i; j++) *(biggerArrayPtr + j) = *(originalArrayPtr + j);
            /* free the old memory */
            free(originalArrayPtr);
            /* point the originalArrayPtr variable to the new array */
            originalArrayPtr = biggerArrayPtr;
        }
        char *str = (char *) malloc(sizeof(char) * (STRSIZE + 1));
        printf("Enter your string: ");
        scanf("%s", str);
        scanf("%c", &junk); /* eat up return left in the buffer */
        
        /* assign the string to the currently free location in the array */
        *(originalArrayPtr + i) = str;
        i++;
        /* Ask user again */
        printf("Do you want to enter a string? (y/n)");
        scanf("%c", &answer);
    }/*end main while loop*/
    /* make sure that there is a null in the position after the last string */
    *(originalArrayPtr + i) = 0;
    return originalArrayPtr;
}/*end getStringsFromUser*/

void initPtrArray(char **ptr2ptrs, int n) {
    int i = 0;
    for(; i < n; i++)
        *(ptr2ptrs + i) = 0;
}/* end initPtrArray */


/********************************************************
Fill in the code for the following function.
You can use ONE string function, strlen, to help you find the length of a string.
For any other string manipulations, write your own functions.
********************************************************/
char *joinstring(char **stringPtrs, char *delimiter) {
    char *joinedStr = 0;
    /* figure out how much mem we need to allocate */
    int numStr = 0, totLen = 0;
    /* get num strings */
    int n= 0;
    while(stringPtrs[n] != '\0') {
        numStr++;
        totLen += strlen(stringPtrs[n]); //add length of current string
        n++;
    }
    totLen += (strlen(delimiter) * (numStr - 1)) + 1;
    joinedStr = malloc(totLen * sizeof(char));
    
    /* Build the string */
    int spot = 0, i, j, k; //character spot of joinedStr, stringPtrs #, currStr spot, delimiter spot
    for(i = 0; i < numStr; i++) {
        char *currStr = stringPtrs[i]; //temporary string
        //add every character from the currStr to joinedStr
        for(j = 0; j < strlen(currStr); j++) {
            joinedStr[spot] = currStr[j];
            spot++;
        }
        //add every character from the delimiter to joinedStr
        if(spot < totLen - 1) {
            for(k = 0; k < strlen(delimiter); k++) {
                joinedStr[spot] = delimiter[k];
                spot++;
            }
        }
    }
    joinedStr[spot] = '\0'; //add ending null character
    
    return joinedStr;
}

void display(char **stringPtrs, char *delimiter, char *joinedStr) {
    printf("Original strings:\n");
    while(*stringPtrs) {
        printf("%s\n", *stringPtrs);
        stringPtrs++;
    }
    printf("Delimiter: %s\n", delimiter);
    printf("Joined string: %s\n", joinedStr);
    
}/*end display*/

void freemem(char **mem) {
    int i = 0;
    while(*mem) {
        free(*mem);  /* free space for each string */
        *mem = 0; /* zero out the pointer */
        mem++;
    }
}