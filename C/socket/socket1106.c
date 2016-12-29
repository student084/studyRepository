#include <stdlib.h>
#include <stdio.h>
#include "socket1106.h"
#include <string.h>  
#include <errno.h>  
#include <sys/types.h>  
#include <sys/socket.h>  
#include <netinet/in.h> 
#include <sys/types.h>  
#include <signal.h> 
#define DEFAULT_PORT 8000  
#define MAXLINE 4096  
int main(int argc, char** argv)  
{  
    int    socket_fd, connect_fd;  
    struct sockaddr_in     servaddr;  
    char    buff[4096];  
    int     n;  
    FILE*  fp_stream;
    char 	buffer[1000];
    if((fp_stream=popen("ps -A | grep socket1106", "r")) != NULL){
    	while(fgets(buffer, sizeof(buffer), fp_stream)!=NULL){
			buffer[5]	= 0;
			n	= atoi(buffer);
			if(n!=getpid()){
				printf("killing %d with signal %d\n", n, SIGQUIT);
				kill(n, SIGQUIT);
			}
		}

		sleep(1);
    }
    //init Socket  
    if( (socket_fd = socket(AF_INET, SOCK_STREAM, 0)) == -1 ){  
    printf("create socket error: %s(errno: %d)\n",strerror(errno),errno);  
    exit(0);  
    }  
    //set servaddr
    memset(&servaddr, 0, sizeof(servaddr));  
    servaddr.sin_family = AF_INET;  
    servaddr.sin_addr.s_addr = htonl(INADDR_ANY);  
    servaddr.sin_port = htons(DEFAULT_PORT);
  
    //bind self-adress with socket  
    if( bind(socket_fd, (struct sockaddr*)&servaddr, sizeof(servaddr)) == -1){  
    printf("bind socket error: %s(errno: %d)\n",strerror(errno),errno);  
    exit(0);  
    }    
    if( listen(socket_fd, 10) == -1){  
    printf("listen socket error: %s(errno: %d)\n",strerror(errno),errno);  
    exit(0);  
    }  
    printf("======waiting for client's request======\n");  
    while(1){  
   //waiting for userful link。  
        if( (connect_fd = accept(socket_fd, (struct sockaddr*)NULL, NULL)) == -1){  
        printf("accept socket error: %s(errno: %d)",strerror(errno),errno);  
        continue;  
    }  
    //recive data from pc
    n = recv(connect_fd, buff, MAXLINE, 0);   
    if(!fork()){  
        if(send(connect_fd, "Hello,you are connected!\n", 26,0) == -1)  
        perror("send error");  
        close(connect_fd); 
        close(connect_fd);  
        exit(0);  
    }  
    buff[n] = '\0';
    if(buff[0] == 'S'){
    	system("shutdown -s -t 0");
    }  
    printf("recv msg from client: %s\n", buff);  
    close(connect_fd);  
    }  
    close(socket_fd);  
}  
