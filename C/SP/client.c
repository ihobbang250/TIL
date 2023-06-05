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

int main(int argc, char *argv[]) {
    if (argc < 2) {
        printf("Usage: %s <client_id>\n", argv[0]);
        exit(1);
    }

    int client_id = atoi(argv[1]);
    key_t key;
    int server_queue_id, client_queue_id;
    struct message msg;

    if (client_id == 1) {
        key = 0x202712342;  // client1용 키
        msg.mtype = 100;    // client1 메시지 타입
    } else if (client_id == 2) {
        key = 0x202712343;  // client2용 키
        msg.mtype = 200;    // client2 메시지 타입
    } else {
        printf("Invalid client_id\n");
        exit(1);
    }

    // 서버 메시지 큐에 접근
    server_queue_id = msgget(0x202712341, 0666);
    printf("key: %u\n", key);
    if (server_queue_id == -1) {
        perror("Failed to access server message queue");
        exit(1);
    }

    // 클라이언트 메시지 큐 접근
    client_queue_id = msgget(key, 0666);
    if (client_queue_id == -1) {
        perror("Failed to get client message queue");
        exit(1);
    }

    printf("Client %d is running...\n", client_id);
    printf("msg mtype: %ld\n", msg.mtype);

    while (1) {
        printf("Text to send: ");
        fgets(msg.mtext, MAX_MESSAGE_SIZE, stdin);

        // 개행 문자 제거
        msg.mtext[strcspn(msg.mtext, "\n")] = '\0';

        // 메시지 전송
        msgsnd(server_queue_id, &msg, sizeof(struct message) - sizeof(long), 0);
        printf("send to server: %s\n", msg.mtext);

        // 서버로부터 메시지 수신
        if (client_id == 1) {
            msgrcv(client_queue_id, &msg, sizeof(struct message) - sizeof(long), 200, 0);
            printf("recv from client2: %s\n", msg.mtext);
        }

        else if (client_id == 2) {
            memset(msg.mtext, 0, MAX_MESSAGE_SIZE);
            msgrcv(client_queue_id, &msg, sizeof(struct message) - sizeof(long), 100, 0);
            printf("recv from client1: %s\n", msg.mtext);
        }

        // "quit" 메시지 수신 시 종료
        if (strcmp(msg.mtext, "quit") == 0) {
            printf("Received quit message. Exiting...\n");

            msgsnd(server_queue_id, &msg, sizeof(struct message) - sizeof(long), 0);

            // 메시지 큐 제거
            msgctl(client_queue_id, IPC_RMID, NULL);
            exit(0);
        }


    }

    return 0;
}







