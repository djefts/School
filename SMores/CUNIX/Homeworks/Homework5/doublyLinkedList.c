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
    printf("Size of list: %d\n", list.size);
    traverseForward(list, display);
    
    int num3 = 1500;
    prepend(&list, &num3);
    printf("Size of list: %d\n", list.size);
    traverseForward(list, display);
    
    printf("Going backward\n");
    traverseBackward(list, display);
    
    printf("Removing front\n");
    removeFromFront(&list);
    traverseForward(list, display);
    
    printf("Removing back\n");
    removeFromBack(&list);
    traverseForward(list, display);
    
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
     * This removes, and returns, the data from the data at the front of the list or NULL if there is nothing to remove.
     * Remember to free the NODE.
     */
    if(listPtr == NULL) return NULL;
    
    LIST_NODE *node = listPtr->front;
    void *data = node->dataPtr;
    listPtr->front = listPtr->front->next;
    
    //TODO: Free data
    free(node);
    
    return data;
}

void *removeFromBack(LINKED_LIST *listPtr) {
    /*
     * This removes the node at the back of the list and returns its data or NULL if there is nothing to remove.
     * Remember to free the NODE.
     */
    if(listPtr == NULL) return NULL;
    
    LIST_NODE *node = listPtr->back;
    void *data = node->dataPtr;
    listPtr->back = listPtr->back->prev;
    
    //TODO: Free node
    free(node);
    
    return data;
}
