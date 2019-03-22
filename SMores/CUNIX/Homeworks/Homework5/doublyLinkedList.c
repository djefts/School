/**
 * @name doublyLinkedList.c
 * @author David Jefts
 * @date 3/21/2019
 * @details C program of functions to play with doubly-linked lists
 *          Does not accept any command line arguments, does use command line prompts
*/
#include <stdio.h>
#include <stdlib.h>

typedef struct list_node {
    void *dataPtr;
    struct list_node *next;
    struct list_node *prev;
} LIST_NODE;

typedef struct linkedList {
    int size;
    LIST_NODE *front, *back;
} LINKED_LIST;

int mainMethod();

int test();

void append(LINKED_LIST *list, void *data);

void prepend(LINKED_LIST *listPtr, void *data);

void traverseForward(LINKED_LIST head, void function(void *));

void traverseBackward(LINKED_LIST head, void function(void *));

void display(void *data);

/* homework methods */
void *removeFromFront(LINKED_LIST *listPtr);

void *removeFromBack(LINKED_LIST *listPtr);

int insert(LINKED_LIST *listPtr, void *data, int position);

void *removeAtPosition(LINKED_LIST *listPtr, int position);

void deleteList(LINKED_LIST *listPtr);

void *copyData(void *data);

LINKED_LIST *copyList(LINKED_LIST list, void *copyData(void *));

LINKED_LIST *mergeLists(LINKED_LIST list1, LINKED_LIST list2, void *copyData(void *));

void splitList(LINKED_LIST *originalList, LINKED_LIST *listPart2, int splitPosition);

int main(int argc, char *argv[]) {
    mainMethod();
    
    /* testing. testing. testing... */
    //test();
    
    return 0;
}

int mainMethod() {
    /*
     * This function will work with lists of integers.
     * It creates two empty LINKED_LIST variables and then presents the user with a menu of choices for testing all of
     *      the primary functions in this doublyLinkedList.c file, including those that were already there:
     *      append, prepend, removeFromFront, removeFromBack, insert, remove, mergeLists, and splitList.
     * Based on user response, the appropriate functions are called.
     *
     * There should be a function for presenting the menu and returning the response as well as a function for handling
     *      the response.
     *
     * The choices that affect one l only should ask the user which l they wish to affect and proceed accordingly
     *      on the l chosen.
     *
     * The user should be able to continuously make selections as long as she does not select whichever option for
     *      quitting is in the menu.
     *
     * Make sure to call the deleteList function to destroy any lists that were created and are no longer in use.
     *
     * Every time a l is modified or created, the traverseForward function should be called on the l.
     */
    
    LINKED_LIST one; //first l
    one.front = one.back = NULL;
    one.size = 0;
    LINKED_LIST two; //second l
    two.front = two.back = NULL;
    two.size = 0;
    
    char input; //menu selection
    int l = 0; //chosen list number pointer for single-list options
    LINKED_LIST *list = NULL; //chosen list pointer for single-list options
    LINKED_LIST *non = NULL; //non-chosen list pointer for single-list options
    int data = 0; //added data pointer
    int spot = 0; //position to insert/remove pointer
    do {
        //reset variables
        input = '\0';
        l = 0;
        list = NULL;
        non = NULL;
        data = 0;
        spot = 0;
        
        /* Menu Options */
        printf("1 - append\n");
        printf("2 - prepend\n");
        printf("3 - removeFromFront\n");
        printf("4 - removeFromBack\n");
        printf("5 - insert\n");
        printf("6 - removeAtPosition\n");
        printf("7 - mergeLists\n");
        printf("8 - splitLists\n");
        printf("9 - Exit Program\n");
        printf("Enter a number corresponding to the method you wish to run:\n");
        scanf(" %c", &input); //discards blanks and reads the first non-whitespace character
        
        //menu
        switch(input) {
            case '1':   //append
                printf("To which list do you wish to append?\n");
                scanf("%d", &l);
                if(l == 1) {
                    list = &one;
                } else if(l == 2) {
                    list = &two;
                } else {
                    printf("Invalid input, please try again.\n\n");
                    break;
                }
                
                printf("Please enter the number you wish to append.\n");
                scanf("%d", &data);
                
                append(list, copyData(&data));
                break;
            case '2':   //prepend
                printf("To which list do you wish to prepend?\n");
                scanf("%d", &l);
                if(l == 1) {
                    list = &one;
                } else if(l == 2) {
                    list = &two;
                } else {
                    printf("Invalid input, please try again.\n\n");
                    break;
                }
                
                printf("Please enter the number you wish to prepend.\n");
                scanf("%d", &data);
                
                prepend(list, &data);
                break;
            case '3':   //removeFromFront
                printf("Which list do you want to remove the front from?\n");
                scanf("%d", &l);
                
                if(l == 1) {
                    list = &one;
                } else if(l == 2) {
                    list = &two;
                } else {
                    printf("Invalid input, please try again.\n\n");
                    break;
                }
                
                removeFromFront(list);
                break;
            case '4':   //removeFromBack
                printf("Which list do you want to remove the back from?\n");
                scanf("%d", &l);
                
                if(l == 1) {
                    list = &one;
                } else if(l == 2) {
                    list = &two;
                } else {
                    printf("Invalid input, please try again.\n\n");
                    break;
                }
                
                removeFromBack(list);
            case '5':   //insert
                printf("To which list do you wish to insert data?\n");
                scanf("%d", &l);
                if(l == 1) {
                    list = &one;
                } else if(l == 2) {
                    list = &two;
                } else {
                    printf("Invalid input, please try again.\n\n");
                    break;
                }
                
                printf("Please enter the number you wish to insert.\n");
                scanf("%d", &data);
                printf("Please enter the where you wish to insert the data.\n");
                scanf("%d", &spot);
                
                insert(list, &data, spot);
                break;
            case '6':   //removeAtPosition
                printf("From which list do you wish to remove data?\n");
                scanf("%d", &l);
                if(l == 1) {
                    list = &one;
                } else if(l == 2) {
                    list = &two;
                } else {
                    printf("Invalid input, please try again.\n\n");
                    break;
                }
                
                printf("Please enter the node number from where you wish to remove data.\n");
                scanf("%d", &spot);
                
                removeAtPosition(list, spot);
                break;
            case '7':   //mergeLists
                printf("Merging lists...\n");
                mergeLists(one, two, copyData);
                break;
            case '8':   //splitLists
                printf("Which list do you want to split?\n");
                scanf("%d", &l);
                if(l == 1) {
                    list = &one;
                    non = &two;
                } else if(l == 2) {
                    list = &two;
                    non = &one;
                } else {
                    printf("Invalid input, please try again.\n\n");
                    break;
                }
                
                printf("Please enter the node number you wish to split the list at.\n");
                scanf("%d", &spot);
                
                splitList(list, non, spot);
                break;
            case '9':   //exit
                return 0;
            default:    //bad input
                printf("Invalid input, please try again.\n\n");
                break;
        } //end switch
        //display both lists
        printf("List 1:\n");
        traverseForward(one, display);
        printf("List 2:\n");
        traverseForward(two, display);
        printf("\n\n");
    } while(1);
}

int test() {
    int num1 = 314;
    int num2 = 2718;
    int num3 = 161803;
    int num4 = 867 - 5309;
    int num5 = 666;
    int num6 = 42;
    
    LINKED_LIST list;
    list.size = 0;
    list.front = list.back = NULL;
    
    
    LINKED_LIST listTwo;
    listTwo.size = 0;
    listTwo.front = listTwo.back = NULL;
    
    append(&list, &num1);
    printf("Size of list: %d\n", list.size);
    traverseForward(list, display);
    
    append(&list, &num2);
    printf("\nSize of list: %d\n", list.size);
    traverseForward(list, display);
    
    prepend(&list, &num3);
    printf("\nSize of list: %d\n", list.size);
    traverseForward(list, display);
    traverseBackward(list, display);
    
    /* Problem 1 */
    printf("\n\t\tProblem #1 - Removing front\n");
    removeFromFront(&list);
    traverseForward(list, display);
    traverseBackward(list, display);
    
    /* Make list big again */
    printf("\n\t\tAdd another node");
    prepend(&list, &num4);
    printf("\nSize of list: %d\n", list.size);
    traverseForward(list, display);
    traverseBackward(list, display);
    
    /* Problem 2 */
    printf("\n\t\tProblem #2 - Removing back\n");
    removeFromBack(&list);
    traverseForward(list, display);
    traverseBackward(list, display);
    
    /* Make list big again */
    printf("\n\t\tAdd another node");
    prepend(&list, &num3);
    printf("\nSize of list: %d\n", list.size);
    traverseForward(list, display);
    traverseBackward(list, display);
    
    /* Problem 3 */
    printf("\n\t\tProblem #3 - Inserting node %d\n", num5);
    insert(&list, &num5, 1);
    traverseForward(list, display);
    traverseBackward(list, display);
    
    /* Problem 4 */
    printf("\n\t\tProblem #4 - Removing node %d\n", 1);
    /** Function name "remove" is already defined. Changed to "removeFromList" **/
    removeAtPosition(&list, 1);
    traverseForward(list, display);
    traverseBackward(list, display);
    
    /* Problem 5 */
    printf("\n\t\tProblem #5 - Delete the list\n");
    deleteList(&list);
    traverseForward(list, display);
    traverseBackward(list, display);
    
    /* Rebuild the list */
    printf("\n\t\tRebuild the list");
    prepend(&list, &num1);
    prepend(&list, &num2);
    prepend(&list, &num3);
    printf("\nSize of list: %d\n", list.size);
    traverseForward(list, display);
    traverseBackward(list, display);
    
    /* Problem 6 */
    printf("\n\t\tProblem #6 - Merge Two Lists\n");
    traverseForward(list, display);
    traverseBackward(list, display);
    printf("\tCreate a new list\n");
    prepend(&listTwo, &num4);
    prepend(&listTwo, &num5);
    prepend(&listTwo, &num6);
    traverseForward(listTwo, display);
    traverseBackward(listTwo, display);
    printf("\tMerging the lists:\n");
    list = *mergeLists(list, listTwo, copyData);
    traverseForward(list, display);
    traverseBackward(list, display);
    
    /* Problem 7 */
    printf("\n\t\tProblem #6 - Split the list at %d\n", 3);
    LINKED_LIST split;
    split.size = 0;
    split.front = split.back = NULL;
    splitList(&list, &split, 3);
    printf("\tFirst List:\n");
    traverseForward(list, display);
    traverseBackward(list, display);
    printf("\tSecond List:\n");
    traverseForward(split, display);
    traverseBackward(split, display);
    
    return 0;
}

void append(LINKED_LIST *listPtr, void *data) {
    if(data == NULL) return;
    /*get a node to put on the list and add the data to it */
    LIST_NODE *node = (LIST_NODE *) malloc(sizeof(LIST_NODE));
    node->dataPtr = data;
    node->next = NULL;
    node->prev = NULL;
    
    listPtr->size++;
    if(listPtr->front == NULL) /* See if it's the first to go on the list */
    {
        listPtr->front = listPtr->back = node;
        return;
    }
    
    listPtr->back->next = node;
    node->prev = listPtr->back;
    listPtr->back = node;
}

void prepend(LINKED_LIST *listPtr, void *data) {
    if(data == NULL) return;
    /*get a node to put on the list and add the data to it */
    LIST_NODE *node = (LIST_NODE *) malloc(sizeof(LIST_NODE));
    node->dataPtr = data;
    node->next = NULL;
    node->prev = NULL;
    
    listPtr->size++;
    if(listPtr->front == NULL) /* See if it's the first to go on the list */
    {
        listPtr->front = listPtr->back = node;
        return;
    }
    
    node->next = listPtr->front;
    listPtr->front->prev = node;
    listPtr->front = node;
}

void traverseForward(LINKED_LIST head, void function(void *)) {
    printf("Forwards:\n");
    LIST_NODE *front = head.front;
    while(front) {
        function(front->dataPtr);
        front = front->next;
    }
}

void traverseBackward(LINKED_LIST head, void function(void *)) {
    printf("Backward:\n");
    LIST_NODE *back = head.back;
    while(back) {
        function(back->dataPtr);
        back = back->prev;
    }
}


void display(void *data) {
    int *ptr = (int *) data;
    printf("%d\n", *ptr);
}

/**
 * Problem #1
 */
void *removeFromFront(LINKED_LIST *listPtr) {
    /*
     * This removes, and returns, the data from the data at the front of the list or NULL if there is nothing to
     *      removeAtPosition.
     * Remember to free the NODE.
     */
    if(listPtr == NULL || listPtr->front == NULL) return NULL;
    
    listPtr->size--;
    LIST_NODE *node = listPtr->front;
    void *data = node->dataPtr;
    //last node condition
    if(listPtr->size == 0) {
        listPtr->front = NULL;
        listPtr->back = NULL;
    } else {
        listPtr->front = listPtr->front->next;
        listPtr->front->prev = NULL;
    }
    
    //Free node
    free(node);
    
    return data;
}

/**
 * Problem #2
 */
void *removeFromBack(LINKED_LIST *listPtr) {
    /*
     * This removes the node at the back of the list and returns its data or NULL if there is nothing to removeAtPosition.
     * Remember to free the NODE.
     */
    if(listPtr == NULL || listPtr->front == NULL) return NULL;
    
    listPtr->size--;
    LIST_NODE *node = listPtr->back;
    void *data = node->dataPtr;
    //last node condition
    if(listPtr->size == 0) {
        listPtr->front = NULL;
        listPtr->back = NULL;
    } else {
        listPtr->back = listPtr->back->prev; //skip the node
        listPtr->back->next = NULL; //set next to none
    }
    
    //Free node
    free(node);
    
    return data;
}

/**
 * Problem #3
 */
int insert(LINKED_LIST *listPtr, void *data, int position) {
    /*
     * This inserts the data at the given position in the list as long as the position is bigger than or equal to 0 and
     *      less than or equal to the current size of the list.
     * If the position is out of range, the function returns -1.
     * Inserting at position 0 means adding to the front and inserting at the position equal to the size means adding to
     *      the back.
     * If all is well, the function returns a 0.
     */
    if(listPtr == NULL || listPtr->front == NULL) return -1;
    if(position > listPtr->size | position < 0) return -1;
    
    //lil' bit o' optimization
    if(position == 0) {
        prepend(listPtr, data);
        return 0;
    } else if(position == listPtr->size) {
        append(listPtr, data);
        return 0;
    }
    
    //create the new node
    listPtr->size++;
    LIST_NODE *node = (LIST_NODE *) malloc(sizeof(LIST_NODE));
    node->dataPtr = data;
    node->next = NULL;
    node->prev = NULL;
    
    //get to the insertion spot
    LIST_NODE *curr = listPtr->front;
    int spot = 0;
    while(spot < position) {
        curr = curr->next;
        spot++;
    }
    
    //insert the node
    node->next = curr->next;
    node->prev = curr;
    
    curr->next->prev = node;
    curr->next = node;
    
    return 0;
}

/**
 * Problem #4
 */
void *removeAtPosition(LINKED_LIST *listPtr, int position) {
    /*
     * This removes the node at the given position in the list as long as the position is bigger than or equal to 0 and
     *      less than the current size of the list.
     * If the position is out of range, the function returns NULL.
     * Otherwise, it returns the data of the node at that position.
     * Removing at position 0 means removing the front and removing at the position one less than the size means
     *      removing from the back. If all is well, the function returns the data of the node to be removed.
     * Remember to free the NODE.
     */
    if(listPtr == NULL || listPtr->front == NULL) return NULL;
    if(position >= listPtr->size | position < 0) return NULL;
    
    //lil' bit o' optimization
    if(position == 0) {
        printf("Removing from front instead");
        return removeFromFront(listPtr);
    } else if(position == listPtr->size - 1) {
        printf("Removing from back instead");
        return removeFromBack(listPtr);
    }
    
    //get to the removal spot
    LIST_NODE *curr = listPtr->front;
    int spot = 0;
    while(spot < position - 1) {
        curr = curr->next;
        spot++;
    }
    
    listPtr->size--;
    LIST_NODE *node = listPtr->back;
    void *data = node->dataPtr;
    //last node condition
    if(listPtr->size == 0) {
        listPtr->front = NULL;
        listPtr->back = NULL;
    } else {
        listPtr->back = listPtr->back->prev; //skip the node
        listPtr->back->next = NULL; //set next to none
    }
    
    free(node);
    
    return data;
}

/**
 * Problem #5
 */
void deleteList(LINKED_LIST *listPtr) {
    /*
     * This deletes the data pointed to by each node of the list as well as the node itself.
     * Remember to set front and back to NULL after all nodes have been deleted.
     */
    if(listPtr == NULL || listPtr->front == NULL) return;
    while(listPtr->front && listPtr->back) {
        /** Both will work, this is just semantics **/
        removeFromFront(listPtr);
        //removeFromBack(listPtr);
    }
}

void *copyData(void *data) {
    /*
     * This function should be specific to handling integer data that goes on the list.
     * See my display function that prints integer data to see how it is specific to integer data.
     */
    int *ptr = (int *) data;
    void *out = ptr;
    return out;
}

LINKED_LIST *copyList(LINKED_LIST list, void *copyData(void *)) {
    /*
     * This function makes a copy of the list parameter and uses the copyData function to create a copy of the data
     *      pointed to by each node.
     */
    //create the new list
    LINKED_LIST *newList = (LINKED_LIST *) calloc(1, sizeof(LINKED_LIST));
    newList->size = 0;
    newList->front = list.back = NULL;
    
    append(newList, copyData(list.front->dataPtr)); //append the first node
    
    LIST_NODE *curr = list.front->next; //pointer to go through input list
    void *data;
    while(curr) { //loop through input list
        data = curr->dataPtr; //get the data
        data = copyData(data); //copy the data
        append(newList, data); //append the data
        curr = curr->next;
    }
    
    return newList;
}

LINKED_LIST *mergeLists(LINKED_LIST list1, LINKED_LIST list2, void *copyData(void *)) {
    /*
     * This creates a NEW list comprised of COPY of list2attached to the end of a COPY of list1.
     * Note that this function does NOT modify EITHER list and that you must make complete COPIES of each node as well
     *      as the data that each node points to.
     * That is why you have to pass in the copyData function (see description later on in this document) since it is the
     *      only thing that knows what the data looks like.
     * This function should call copyList() to create a copy of the given list.
     */
    LINKED_LIST *frontList = copyList(list1, copyData);
    LINKED_LIST *backList = copyList(list2, copyData);
    frontList->back->next = backList->front; //front back to back front
    backList->front->prev = frontList->back; //back front to front back
    frontList->back = backList->back; //front back to back back
    //do not need to "back front to front front"
    
    frontList->size = list1.size + list2.size;
    
    free(backList);
    deleteList(&list1);
    deleteList(&list2);
    
    return frontList;
}

void splitList(LINKED_LIST *originalList, LINKED_LIST *listPart2, int splitPosition) {
    /*
     * This function modifies the original list so that it now ends at splitPosition –1 and the new list starts at
     *      splitPosition.
     * Therefore, splitPosition has to be from 1 to (size –1).
     * If splitPosition is out of range, the function does nothing.
     */
    if(originalList == NULL || originalList->front == NULL) return; //input list is empty
    if(splitPosition > originalList->size || splitPosition < 1) return;
    
    LIST_NODE *curr = originalList->front;
    int spot = 0;
    while(curr && spot < splitPosition - 1) {
        curr = curr->next;
        spot++;
    }
    //build second 'half'
    listPart2->front = curr;
    listPart2->back = originalList->back;
    listPart2->size = originalList->size - splitPosition + 1;
    
    //break off first half
    //originalList->front = originalList->front;
    originalList->back = curr->prev;
    originalList->back->next = NULL; //cut first half off from second half
    originalList->size = splitPosition - 1;
    
    //have to do this last otherwise can't set orig->back to curr->prev
    listPart2->front->prev = NULL; //cut second half off from first half
}
