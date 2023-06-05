#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/msg.h>

#define MAX_MESSAGE_SIZE 1000

struct message {
    long mtype;
    char mtext[MAX_MESSAGE_SIZE];
};

int main() {
    key_t key1 = 0x202712341;  // 서버용 키
    int server_queue_id, client1_queue_id, client2_queue_id;
    struct message msg;

    // 서버용 메시지 큐 생성
    server_queue_id = msgget(key1, IPC_CREAT | 0666);
    if (server_queue_id == -1) {
        perror("Failed to create server message queue");
        exit(1);
    }

    // client1용 메시지 큐 생성
    key_t key2 = 0x202712342;
    client1_queue_id = msgget(key2, IPC_CREAT | 0666);
    if (client1_queue_id == -1) {
        perror("Failed to create client1 message queue");
        exit(1);
    }

    // client2용 메시지 큐 생성
    key_t key3 = 0x202712343;
    client2_queue_id = msgget(key3, IPC_CREAT | 0666);
    if (client2_queue_id == -1) {
        perror("Failed to create client2 message queue");
        exit(1);
    }

    printf("Server is running...\n");

    while (1) {
        // client1 -> server
        msgrcv(server_queue_id, &msg, sizeof(struct message) - sizeof(long), 100, 0);
        printf("recv from client1: %s, type: %ld\n", msg.mtext, msg.mtype);
        printf("step1 clear\n");
        
        // server -> client2
        msgsnd(client2_queue_id, &msg, sizeof(struct message) - sizeof(long), 0);
        printf("send to client2: %s, type: %ld\n", msg.mtext, msg.mtype);
        printf("step2 clear\n");

        // client2 -> server
        msgrcv(server_queue_id, &msg, sizeof(struct message) - sizeof(long), 200, 0);
        printf("recv from client2: %s, type: %ld\n", msg.mtext, msg.mtype);
        printf("step3 clear\n");

        // server -> client1
        msgsnd(client1_queue_id, &msg, sizeof(struct message) - sizeof(long), 0);
        printf("send to client1: %s, type: %ld\n", msg.mtext, msg.mtype);
        printf("step4 clear\n");
        memset(msg.mtext, 0, MAX_MESSAGE_SIZE);

        // "quit" 메시지 수신 시 종료
        if (strcmp(msg.mtext, "quit") == 0) {
            printf("Received server quit. Exiting...\n");
            // 메시지 큐 제거
            msgctl(server_queue_id, IPC_RMID, NULL);
            exit(0);
        }
    }

    return 0;
}




