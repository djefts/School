#include <sys/types.h>
#include <sys/stat.h>
#include <time.h>
#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <string.h>

/*
    struct stat {
        dev_t     st_dev;         ID of device containing file
        ino_t     st_ino;         Inode number
        mode_t    st_mode;        File type and mode
        nlink_t   st_nlink;       Number of hard links
        uid_t     st_uid;         User ID of owner
        gid_t     st_gid;         Group ID of owner
        dev_t     st_rdev;        Device ID (if special file)
        off_t     st_size;        Total size, in bytes
        blksize_t st_blksize;     Block size for filesystem I/O
        blkcnt_t  st_blocks;      Number of 512B blocks allocated

        Since Linux 2.6, the kernel supports nanosecond
        precision for the following timestamp fields.

        struct timespec st_atim;  Time of last access
        struct timespec st_mtim;  Time of last modification
        struct timespec st_ctim;  Time of last status change

		#define st_atime st_atim.tv_sec      Backward compatibility
        #define st_mtime st_mtim.tv_sec
        #define st_ctime st_ctim.tv_sec
    };
*/

void displayStatInfo(struct stat *);

void displayFilePermissions(unsigned long);

int main(int argc, char *argv[]) {
    struct stat stInfo;
    
    if(argc != 2) {
        printf("Usage: simpleStat filename\n");
        return 0;
    }
    
    printf("Filename: %s\n", argv[1]);
    
    if(stat(argv[1], &stInfo) == -1) /* something went wrong */
    {
        perror("Error using stat");
        exit(EXIT_FAILURE);
    }
    
    displayStatInfo(&stInfo);
    
    displayFilePermissions(stInfo.st_mode);
    
    return 0;
}/* end main */

void displayStatInfo(struct stat *stInfoPtr) {
    printf("I-node number: %ld\n", (long) stInfoPtr->st_ino);
    printf("Mode: %lo\n", (unsigned long) stInfoPtr->st_mode); /* displayed in octal */
    printf("File size: %lld bytes\n", (long long) stInfoPtr->st_size);
    printf("Optimal I/O block size: %ld bytes\n", (long) stInfoPtr->st_blksize);
    printf("512B blocks allocated: %lld\n", (long long) stInfoPtr->st_blocks);
    printf("Last file access:         %s\n", ctime(&stInfoPtr->st_atime));
    printf("Last file modification:   %s\n", ctime(&stInfoPtr->st_mtime));
    printf("Last status change:       %s\n", ctime(&stInfoPtr->st_ctime));
}

void displayFilePermissions(unsigned long mode) {
    unsigned long permissions, perm;
    char strPerm[] = "   ";
    int i;
    printf("Mode: %lo\n", mode);
    permissions = mode % 32768;
    //printf("%lo\n", permissions); //100000 octal = 32768 decimal
    
    for(i = 0; i < 3; i++) {
        perm = permissions % 8;
        //printf("%lo\n", perm);
        
        switch(perm) {
            case 0:
                strcpy(strPerm, "---");
                break;
            case 1:
                strcpy(strPerm, "--x");
                break;
            case 2:
                strcpy(strPerm, "-w-");
                break;
            case 3:
                strcpy(strPerm, "-wx");
                break;
            case 4:
                strcpy(strPerm, "r--");
                break;
            case 5:
                strcpy(strPerm, "r-x");
                break;
            case 6:
                strcpy(strPerm, "rw-");
                break;
            case 7:
                strcpy(strPerm, "rwx");
                break;
            default:
                perror("Error with permissions digit");
                exit(1);
        }
        
        if(i == 0) {
            printf("World/Other Permissions:\t%lo: %s\n", perm, strPerm);
        } else if(i == 1) {
            printf("Group Permissions:\t\t%lo: %s\n", perm, strPerm);
        } else if(i == 2) {
            printf("User Permissions:\t\t%lo: %s\n", perm, strPerm);
        }
        permissions /= 8; //next digit of the permissions code
    }
}
