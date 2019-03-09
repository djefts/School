/*
	doublyLinkedList.c
	DGB
	2/24/2019
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

void append(LINKED_LIST *list, void *data);

void prepend(LINKED_LIST *listPtr, void *data);

void traverseForward(LINKED_LIST head, void function(void *));

void traverseBackward(LINKED_LIST head, void function(void *));

void display(void *data);

/* homework methods */
void *removeFromFront(LINKED_LIST *listPtr);

void *removeFromBack(LINKED_LIST *listPtr);

int insert(LINKED_LIST *listPtr, void *data, int position);

void *removeFromList(LINKED_LIST *listPtr, int position);

int main(int argc, char *argv[]) {
    int num1 = 128;
    LINKED_LIST list;
    list.size = 0;
    list.front = list.back = NULL;
    
    append(&list, &num1);
    printf("Size of list: %d\n", list.size);
    traverseForward(list, display);
    
    int num2 = -90;
    append(&list, &num2);
    printf("\nSize of list: %d\n", list.size);
    traverseForward(list, display);
    
    int num3 = 1500;
    prepend(&list, &num3);
    printf("\nSize of list: %d\n", list.size);
    traverseForward(list, display);
    
    printf("\nGoing backward\n");
    traverseBackward(list, display);
    
    /* Problem 1 */
    printf("\nProblem #1 - Removing front\n");
    removeFromFront(&list);
    printf("Forwards:\n");
    traverseForward(list, display);
    printf("Backward:\n");
    traverseBackward(list, display);
    
    /* Problem 2 */
    printf("\nProblem #2 - Removing back\n");
    removeFromBack(&list);
    printf("Forwards:\n");
    traverseForward(list, display);
    printf("Backward:\n");
    traverseBackward(list, display);
    
    // add back the removed node
    prepend(&list, &num3);
    printf("\nAdding a node. Size of list: %d\n", list.size);
    printf("Forwards:\n");
    traverseForward(list, display);
    printf("Backward:\n");
    traverseBackward(list, display);
    
    /* Problem 3 */
    int num4 = 666;
    printf(num4);
    printf("\nProblem #3 - Inserting node %d\n", num4);
    printf("HELLO");
    if(insert(&list, &num4, 1) == 0) printf("Success!\n");
    else exit(1);
    printf("Forwards:\n");
    traverseForward(list, display);
    printf("Backward:\n");
    traverseBackward(list, display);
    
    /* Problem 4 */
    printf("\nProblem #4 - Removing node %d\n", 1);
    /** Function name "remove" is already defined. Changed to "removeFromList" **/
    removeFromList(&list, 1);
    printf("Forwards:\n");
    traverseForward(list, display);
    printf("Backward:\n");
    traverseBackward(list, display);
    
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
    
    listPtr->front->prev = node;
    node->next = listPtr->front;
    listPtr->front = node;
}

void traverseForward(LINKED_LIST head, void function(void *)) {
    LIST_NODE *front = head.front;
    while(front) {
        function(front->dataPtr);
        front = front->next;
    }
}

void traverseBackward(LINKED_LIST head, void function(void *)) {
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

void *removeFromFront(LINKED_LIST *listPtr) {
    /*
     * This removes, and returns, the data from the data at the front of the list or NULL if there is nothing to removeFromList.
     * Remember to free the NODE.
     */
    if(listPtr == NULL) return NULL;
    
    listPtr->size--;
    LIST_NODE *node = listPtr->front;
    void *data = node->dataPtr;
    listPtr->front = listPtr->front->next;
    listPtr->front->prev = NULL;
    
    //Free node
    free(node);
    
    return data;
}

void *removeFromBack(LINKED_LIST *listPtr) {
    /*
     * This removes the node at the back of the list and returns its data or NULL if there is nothing to removeFromList.
     * Remember to free the NODE.
     */
    if(listPtr == NULL) return NULL;
    
    listPtr->size--;
    LIST_NODE *node = listPtr->back;
    void *data = node->dataPtr;
    listPtr->back = listPtr->back->prev; //skip the node
    listPtr->back->next = NULL; //set next to none
    
    //Free node
    free(node);
    
    return data;
}

int insert(LINKED_LIST *listPtr, void *data, int position) {
    printf("made it here");
    if(listPtr == NULL) return -1;
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

void *removeFromList(LINKED_LIST *listPtr, int position) {
    if(listPtr == NULL) return NULL;
    if(position >= listPtr->size | position < 0) return NULL;
    
    //lil' bit o' optimization
    if(position == 0) {
        return removeFromFront(listPtr);
    } else if(position == listPtr->size - 1) {
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
    listPtr->back = listPtr->back->prev; //skip the node
    listPtr->back->next = NULL; //set next to none
    
    //TODO: Free node
    free(node);
    
    return data;
}
