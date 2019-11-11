//import statements
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

//generically define the methods to be used
short isValidExpression(char *, char *, char *);

short isOperator(char *);

float calc(int, char *, int);

void printError(short);

//main method
int main(int argc, char *argv[]) {
    short isExp; //variable
    
    if(argc != 4) {
        printf("Usage: calc operand1 operator operand2\n");
        return 1;
    }

    if((isExp = isValidExpression(argv[1], argv[2], argv[3])) == 0) {
        float res;
        int operand1 = atoi(argv[1]); //convert str -> int
        int operand2 = atoi(argv[3]); //convert str -> int
        char *oper = argv[2];
        if(strcmp(oper, "/") == 0 && operand2 == 0) { //string comparison
            printf("Division by zero.\n");
            return 2;
        }
        res = calc(operand1, oper, operand2);
        printf("%d %s %d = %f\n", operand1, oper, operand2, res);
    } else {
        printError(isExp);
        return 3;
    }
    
    return 0;
}/* end main*/

short isValidExpression(char *operand1, char *oper, char *operand2) {
    int oprand1, oprand2;
    oprand1 = atoi(operand1); //convert str -> int
    if(oprand1 == 0 && strcmp(operand1, "0") != 0) {
        return 1;
    }
    
    oprand2 = atoi(operand2); //convert str -> int
    if(oprand2 == 0 && strcmp(operand2, "0") != 0) {
        return 2;
    }
    
    if(!isOperator(oper)) {
        return 3;
    }
    
    return 0;
}

short isOperator(char *oper) {
    char isOper;
    
    if(strlen(oper) != 1) {
        return 0;
    }
    
    switch(oper[0]) {
        case '+':
            isOper = 1;
            break;
        case '-':
            isOper = 2;
            break;
        case '*':
            isOper = 3;
            break;
        case '/':
            isOper = 4;
            break;
        default:
            isOper = 0;
    }
    
    return isOper;
}

float calc(int op1, char *oper, int op2) {
    float result;
    
    switch(isOperator(oper)) {
        case 1:
            result = op1 + op2;
            break;
        case 2:
            result = op1 - op2;
            break;
        case 3:
            result = op1 * op2;
            break;
        case 4:
            result = (float) op1 / op2;
            break;
    }
    
    return result;
}

void printError(short err) {
    switch(err) {
        case 1:
            printf("Invalid operand 1\n");
            break;
        case 2:
            printf("Invalid operand 2\n");
            break;
        case 3:
            printf("Invalid operator\n");
            break;
        default:
            printf("Unknown error\n");
    }
}
