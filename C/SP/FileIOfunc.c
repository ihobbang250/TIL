#include <stdio.h>

int main() {
    FILE *fp;
    char buffer[100];
    char data[] = "hello world";

    fp = fopen("data.bin", "wb");
    if (fp == NULL) {
        perror("data.bin");
        return 1;
    }
    fwrite(data, sizeof(char), sizeof(data), fp);
    fclose(fp);

    fp = fopen("data.bin", "rb");
    if (fp == NULL) {
        perror("data.bin");
        return 1;
    }

    size_t nread = fread(buffer, sizeof(char), sizeof(buffer), fp);
    if (nread == 0) {
        if (feof(fp))
            printf("End of file\n");
        else
            printf("Error reading\n");
    } else {
        printf("%zu bytes read %s\n", nread, buffer);
    }

    fclose(fp);
}