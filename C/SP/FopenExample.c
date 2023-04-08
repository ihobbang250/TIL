#include <stdio.h>

int main() {
    FILE *fp;

    fp = fopen("example.txt", "w"); // file open

    if (fp == NULL) {
        printf("Error: Could not open");
        return 1;
    }

    fprintf(fp, "Hello, world!");

    fclose(fp);

    fp = freopen("example.txt", "w", fp);

    if (fp == NULL) {
        printf("Error: Could not open");
        return 1;
    }

    fprintf(fp, "This is a new world!");

    fclose(fp);

    return 0;   
}