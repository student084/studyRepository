#ifndef _MAIN_H
#define _NAIN_H
#include <stdio.h>
#include <stdlib.h>
#include <sys/mman.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <string.h>
#include <unistd.h>
#define SHARE_MEMORY_FILENAME	"/home/users/wangjian/Study/C/1110/sh"





typedef struct memory_s{
	int log;
	char* data;
}memory, *p_memory;
#endif
