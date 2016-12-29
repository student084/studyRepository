#include "share.h"

int write_sh(p_memory, char*);
int read_sh(p_memory);

int main(int argc, char* argv[]){
	int fd;
	p_memory share_memory;
	fd = shm_open(SHARE_MEMORY_FILENAME, O_RDWR|O_CREAT, 0777);
	if(fd == -1){
		printf("sorry ,there is a error when shm_open");
		exit(0);
	}
	share_memory = (p_memory)malloc(sizeof(memory));
	share_memory = mmap(NULL, sizeof(memory), PROT_READ|PROT_WRITE, MAP_SHARED, fd, 0);
	if(share_memory == MAP_FAILED){
		printf("sorry, there is a error when mmap");
		exit(0);
	}
	if(!fork()){
		while(1){
			read_sh(share_memory);
		}		
	}
	while(1){
		write_sh(share_memory, "hello ");
	}
	return 0;
}

int write_sh(p_memory p, char* str){
	if(p->log == 0){
		p->log = 1;
		memcpy(p->data, str, strlen(str));
	}
	return 0;
}
int read_sh(p_memory p){
	if(p->log == 1){
		p->log = 0;
		printf("have a new massage: %s\n", p->data);
		remove(p->data);
	}
	return 0;
}
