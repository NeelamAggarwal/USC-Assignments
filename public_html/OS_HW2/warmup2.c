#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/time.h>
#include <sys/stat.h>
#include <errno.h>
#include <string.h>
#include <unistd.h>
#include <math.h>
#include <signal.h>
#include "my402list.h"
//#include "my402list.c"
#define INFINITE 10000000000

typedef struct token_type
{

	long double tokenid;
	//unsigned long int tokenrate;
	long double timestampt;
} tokentype;
typedef struct packet_type
{
	long double packetid;
	long double packetrate;
	long double nooftokensneeded; //no of tokens needed for the packet to be removed from Q
	long double servicetime;
	struct timeval Q1start;
	struct timeval Q1end;
	struct timeval Q2start;
	struct timeval Q2end;
	//long double timestampdiff;
} packettype;
typedef struct token_buffer
{
	long double bucketsize;
	long double tokenrate;
	long double noofdroppedtokens;
	long double totalnooftokens;
} tokenbuffertype;
typedef struct packet_buffer
{
	long double totalnoofpackets;
	long double noofdroppedpackets;
	long double allpackets;
} packetbuffertype;

pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t signalserver= PTHREAD_COND_INITIALIZER;
pthread_cond_t waitfortokens= PTHREAD_COND_INITIALIZER;
My402List *Queue1; My402List *Queue2;
tokenbuffertype *tokenbuffer; packetbuffertype *packetbuffer;
//struct timeval lambda.tv_sec=0.5; struct timeval mu.tv_sec=0.35; struct timeval r.tv_sec=1.5; 
struct timeval globalstart;
long double lambda=0.5; long double mu=0.35; long double r=1.5; 
long double B=10; long double P=3; long double num=20;
long double noofservedpackets=0; int pid=0; int tid=0; 
//long double saretokens; long double sarepackets;
long double tempp=20;
long double avgpacketrate=0; long double avgservicerate=0; long double avgpacketq1=0; long double avgpacketq2=0; long double avgpacketboth=0;
long double avgpackettotal=0,squaredurationtotal=0; int stopserver=0;
int stoptoken=0;
void curtime(struct timeval time)
{
	long double milli;
	milli=(time.tv_sec*1000000+time.tv_usec)-(globalstart.tv_sec*1000000+globalstart.tv_usec);
	milli=milli/1000;
	fprintf(stdout,"%012.3Lfms: ",milli);
}
void printtime()
{
	struct timeval time;
	gettimeofday(&time,NULL);
	long double milli;
	milli=(time.tv_sec*1000000+time.tv_usec)-(globalstart.tv_sec*1000000+globalstart.tv_usec);
	milli=milli/1000;
//pthread_mutex_lock(&mutex);
	fprintf(stdout,"%012.3Lfms: ",milli);
//pthread_mutex_unlock(&mutex);
}
/*
void returntime(struct timeval t) 
{
	//int i;
	//for(i=0;i<2;i++);
	gettimeofday(&t,NULL);
printtime();
}
*/
void initializevalue()
{	
	Queue1=(My402List *)malloc(sizeof(My402List));
	Queue2=(My402List *)malloc(sizeof(My402List));
	My402ListInit(Queue1);
	My402ListInit(Queue2);
	tokenbuffer=(tokenbuffertype *)malloc(sizeof(tokenbuffertype));
	packetbuffer=(packetbuffertype *)malloc(sizeof(packetbuffertype));
	tokenbuffer->bucketsize = B;
	//packet->packetrate=strtol(lamba);
	//packet->nooftokensneeded=strtol(P);
	//packet->servicerate=strtol(mu);
	packetbuffer->allpackets=num;
	tempp=num;
//printf("value if tempp intialize to %Lf\n",tempp);
	tokenbuffer->tokenrate=(1/r)*1000000;
}

void *tokenfunction()
{
//printf("Enterring token thread function\n");	
	struct timeval start,end;
//struct timeval startsleep,endsleep;
packettype *temppacket;
	long double duration, timeinsleep,durationpacket;
// long doouble durationsleep;
		timeinsleep=(tokenbuffer->tokenrate);
	My402ListElem *Q1head= malloc(sizeof(My402ListElem));
	//while((pid <= packetbuffer->allpackets) && !My402ListEmpty(Queue1))
	do	
	{	
			usleep(timeinsleep);
			gettimeofday(&start,NULL);
//printf("token while loops begins\n");
//printf("noofservedpacketsin tokenwhile %Lf and allpackets %Lf\n",noofservedpackets,packetbuffer->allpackets);
tokentype *token=malloc(sizeof(tokentype));
			token->tokenid= ++tid;
			//saretokens=tid;
		
		if(tokenbuffer->totalnooftokens >= tokenbuffer->bucketsize)
		{
pthread_mutex_lock(&mutex);
			tokenbuffer->noofdroppedtokens++;  

printtime();
fprintf(stdout,"token t%d arrives, dropped\n",(int)token->tokenid);
pthread_mutex_unlock(&mutex);
//printf("printing no of dropped tokens %Lf\n",tokenbuffer->noofdroppedtokens);
		}
		else
		{
pthread_mutex_lock(&mutex);
			tokenbuffer->totalnooftokens++;
//printf("total no of tokens in token buffer %Lf and bucketsize %Lf \n",tokenbuffer->totalnooftokens,tokenbuffer->bucketsize);
			
printtime();
fprintf(stdout,"token t%d arrives, token bucket now has %d tokens\n",(int)token->tokenid,(int)tokenbuffer->totalnooftokens);
pthread_mutex_unlock(&mutex);
			if(My402ListEmpty(Queue1)==0)
				{
					Q1head = My402ListFirst(Queue1);
					if(tokenbuffer->totalnooftokens >=((packettype*)Q1head->obj)->nooftokensneeded)
					{
							pthread_mutex_lock(&mutex);
							temppacket=(packettype*)Q1head->obj;
							My402ListUnlink(Queue1, Q1head);//unlink packet from Q1					
							printtime();
							gettimeofday(&temppacket->Q1end,NULL);//timestamp packetend in q1

//printf("queue1 head is unlinked having packedid %Lf\n",temppacket->packetid);
durationpacket=(temppacket->Q1end.tv_sec*1000000+temppacket->Q1end.tv_usec)-(temppacket->Q1start.tv_sec*1000000+temppacket->Q1start.tv_usec);
//printf("|||||||||time spend in queue1 is %Lf\n",durationpacket);//timestamp



avgpacketq1=avgpacketq1+durationpacket;
							tokenbuffer->totalnooftokens = tokenbuffer->totalnooftokens - ((packettype*)Q1head->obj)->nooftokensneeded;
//pthread_mutex_lock(&mutex);

fprintf(stdout,"p%d leaves Q1, time in Q1 = %.3Lfms, token bucket now has %d tokens\n",(int)temppacket->packetid,durationpacket/1000,(int)tokenbuffer->totalnooftokens);
//pthread_mutex_unlock(&mutex);

							My402ListAppend(Queue2, Q1head->obj);//append packet to queue2
//printf("queue1 head is appended to queue2 having packetid%Lf\n",((packettype*)Q1head->obj)->packetid);
gettimeofday(&(((packettype*)Q1head->obj)->Q2start),NULL);//timestamp packetstart in q2

//pthread_mutex_lock(&mutex);
printtime();
fprintf(stdout,"p%d enters Q2\n",(int)temppacket->packetid);
//pthread_mutex_unlock(&mutex);
							//timestamp servertime
//printf("signalling server from token function\n");
							pthread_cond_signal( &signalserver );
							pthread_mutex_unlock(&mutex);
					}
				}
		}
		gettimeofday(&end,NULL);
		duration=(end.tv_sec*1000000+end.tv_usec)-(start.tv_sec*1000000+start.tv_usec);
	//printf("in token function *****duration***** %Lf \n",duration);
timeinsleep=(tokenbuffer->tokenrate-duration);
if(timeinsleep<0)
timeinsleep=0.0000001;
//printf("timein*****sleep***** is %Lf\n",timeinsleep);
	//	gettimeofday(&startsleep,NULL);
		
	//	gettimeofday(&endsleep,NULL);
		//durationsleep= (endsleep.tv_sec*1000000+endsleep.tv_usec)-(startsleep.tv_sec*1000000+startsleep.tv_usec);
		//printf("SLEEP *****DURATION***** token%Lf\n",durationsleep);
//sleep(1);

//printf("pid=%d packetbuffer->allpackets=%d My402ListEmpty(Queue1)=%d\n",(int)pid,(int)packetbuffer->allpackets,My402ListEmpty(Queue1));

	}while(stopserver!=1 || My402ListEmpty(Queue1)==FALSE);
//printf("Existing token thread function and noofserved packets is %Lf\n",noofservedpackets);
stoptoken=1;
//printf("exiting token thread\n");
pthread_cond_signal( &signalserver );
return 0;
}
void *packetfunction (void *ptr)
{
//printf("Enterring packet thread function\n");
	struct timeval start,end, startsleep, endsleep;
	packettype *temppacket;
	long double duration, durationsleep,durationpacket, timeinsleep;
	tempp=num;
	FILE *fp;
	fp = (FILE*) ptr;
//printf("in packet function file pointer fp %p\n",fp);
	//fgets(buf, sizeof(buf), fp);
	My402ListElem *Q1head= malloc(sizeof(My402ListElem));
	char buf[5000]; 
	char *temp;
	char *saveptr;
saveptr= malloc(sizeof(char));
	//int noofrows; 
	char *token;
	int columnno=0;
	char delimiters[]=" 	";

	if(fp!=NULL)
	{
//printf(" file pointer is not null and in packetfunction\n");
	  //fgets(buf, sizeof(buf), fp);
	  //token = strtok_r (temp, delimiters, &saveptr);
	  //noofrows=strtold(buf,NULL);
		//packetbuffer->allpackets=noofrows;
		//tempp=noofrows;
		packetbuffer->allpackets=num;
		tempp=num;	  
		//printf("no of rows present in a file%d\n",noofrows);
	  	duration=0;
	  while(tempp--)//noofrows--)
		{	

//printf("in while loop of packet function having fp \n");
			
			packettype *packet;
			packet=malloc(sizeof(packettype));
			packet->packetid= ++pid;

			fgets(buf, sizeof(buf), fp);
			temp=buf;
			temp[strlen(temp)-1]='\0';
			//printf("%s",temp);
			columnno=0;
			columnno++;
			if(columnno==1)
			{
				token = strtok_r (temp, delimiters, &saveptr);      /* token => "words" */
				//printf("firstcolumndatapacketrate%Lf  and %zu \n",strtold(token,NULL),strlen(token));
				columnno++;
				packet->packetrate=strtold(token,NULL);
				packet->packetrate= packet->packetrate*1000;
			}
			if(columnno==2)
			{
				token = strtok_r (NULL, delimiters, &saveptr);      /* token => "words" */
				//printf("secondcolumndata%Lf  and %zu \n",strtold(token,NULL),strlen(token));
				columnno++;
				packet->nooftokensneeded=strtold(token,NULL);
			}
			if(columnno==3)
			{
				token = strtok_r(NULL, delimiters, &saveptr);      /* token => "words" */
				//printf("thirdcolumndataservicetime%Lf  and %zu \n",strtold(token,NULL),strlen(token));
				columnno++;
				packet->servicetime=strtold(token,NULL);
				packet->servicetime=packet->servicetime*1000;
				
			}
			//if(strlen(saveptr)!=0)
			//	printf("Error: More than 3 values in a line in file\n");
			if(columnno<4 || columnno>4)
				{
					fprintf(stderr,"Error: Less or More than 3 values in a line in file\n");
					exit(1);
				}
			gettimeofday(&startsleep,NULL);
timeinsleep=(packet->packetrate)-duration;
			if(timeinsleep<0)
timeinsleep=0.0000001;
			usleep(timeinsleep);	
			gettimeofday(&endsleep,NULL);
			durationsleep= (endsleep.tv_sec*1000000+endsleep.tv_usec)-(startsleep.tv_sec*1000000+startsleep.tv_usec);
			//printf("SLEEP @@@@DURATION@@@@ packet%Lf\n",durationsleep);
			avgpacketrate=avgpacketrate+(durationsleep+duration);

			gettimeofday(&start,NULL);
			//make packets
			pthread_mutex_lock(&mutex);
			printtime();
			fprintf(stdout,"p%d arrives, needs %d tokens, inter-arrival time = %.3Lfms\n",(int)packet->packetid,(int)packet->nooftokensneeded,packet->packetrate/1000);
			pthread_mutex_unlock(&mutex);

			if(packet->nooftokensneeded <= tokenbuffer->bucketsize)
			{	
				packetbuffer->totalnoofpackets++;
//printf("total no of packets in packetbuffer %Lf \n",packetbuffer->totalnoofpackets);
				pthread_mutex_lock(&mutex);
				My402ListAppend(Queue1, packet);//append packet to queue1;
printtime();
fprintf(stdout,"p%d enters Q1\n",(int)packet->packetid);
				gettimeofday(&(packet->Q1start),NULL); 	//timestamp packetstart in q1
//pthread_mutex_lock(&mutex);

//pthread_mutex_unlock(&mutex);
				pthread_mutex_unlock(&mutex);				
			
				if(My402ListEmpty(Queue1)==0)
				{
					Q1head = My402ListFirst(Queue1);
					if(tokenbuffer->totalnooftokens >= ((packettype*)Q1head->obj)->nooftokensneeded)
					//{
						//pthread_mutex_lock(&mutex);
						//NeededTokens=((packettype*)Q1head->obj)->nooftokensneeded;
						//pthread_mutex_unlock(&mutex);
						//pthread_cond_wait( &waitfortokens, &mutex );
						//pthread_mutex_lock(&mutex);
						//NeededTokens=INFINITE;
						//pthread_mutex_unlock(&mutex);
					//}
					//else
					{
						pthread_mutex_lock(&mutex);
						temppacket=(packettype*)Q1head->obj;
						My402ListUnlink(Queue1, Q1head);//unlink packet from Q1
						printtime();
						gettimeofday(&temppacket->Q1end,NULL);

durationpacket=(temppacket->Q1end.tv_sec*1000000+temppacket->Q1end.tv_usec)-(temppacket->Q1start.tv_sec*1000000+temppacket->Q1start.tv_usec);
tokenbuffer->totalnooftokens = tokenbuffer->totalnooftokens - ((packettype*)Q1head->obj)->nooftokensneeded;

fprintf(stdout,"p%d leaves Q1, time in Q1 = %.3Lfms, token bucket now has %d tokens\n",(int)temppacket->packetid,durationpacket/1000,(int)tokenbuffer->totalnooftokens);
//pthread_mutex_unlock(&mutex);

avgpacketq1=avgpacketq1+durationpacket;
						
						My402ListAppend(Queue2, Q1head->obj);//append packet to queue2
						gettimeofday(&(((packettype*)Q1head->obj)->Q2start),NULL);//timestamp packetstart in q2
//printf("packet appended to queue2 with packetid %Lf\n",((packettype*)Q1head->obj)->packetid);
//pthread_mutex_lock(&mutex);
printtime();
fprintf(stdout,"p%d enters Q2\n",(int)temppacket->packetid);
//pthread_mutex_unlock(&mutex);
						//timestamp servertime
//printf("signalling server from packetfunction \n");
						pthread_cond_signal( &signalserver );
						pthread_mutex_unlock(&mutex);
					}
				}
			}
			else
			{
				packetbuffer->noofdroppedpackets++;
				packetbuffer->allpackets--;
				temp--;
pthread_mutex_lock(&mutex);
printtime();
fprintf(stdout,"packet p%d arrives, needs %d tokens, dropped\n",(int)packet->packetid,(int)packet->nooftokensneeded);
pthread_mutex_unlock(&mutex);
//printf("packetdropped havinf packetid %Lf\n",packet->packetid);
			}
			gettimeofday(&end,NULL);
			duration=(end.tv_sec*1000000+end.tv_usec)-(start.tv_sec*1000000+start.tv_usec);
		//	printf("in packet function @@@@duration@@@@ %Lf \n",duration);
			//printf("time in @@@@sleep@@@@ %Lf \n",((1/(packet->packetrate))*1000000)-duration);
			
//avgpacketrate=avgpacketrate+durationsleep;
//sleep(2);
		}
	}
	else
	{	
//printf("value of tempp%Lf\n",tempp);
duration=0;
		while(tempp--)
		{
			packettype *packet;
			packet=malloc(sizeof(packettype));
			packet->packetid= ++pid;
//printf("packet generated having packet id %Lf\n",packet->packetid);
			packet->packetrate= (1/lambda)*1000000;
			packet->servicetime= (1/mu)*1000000;
			packet->nooftokensneeded= P;
timeinsleep=(packet->packetrate)-duration;
			if(timeinsleep<0)
timeinsleep=0.0000001;
			
			gettimeofday(&startsleep,NULL);
			usleep(timeinsleep);	
			gettimeofday(&endsleep,NULL);
		durationsleep= (endsleep.tv_sec*1000000+endsleep.tv_usec)-(startsleep.tv_sec*1000000+startsleep.tv_usec);
		//printf("SLEEP @@@@DURATION@@@@ packet%Lf\n",durationsleep);
			avgpacketrate=avgpacketrate+(durationsleep+duration);

			gettimeofday(&start,NULL);
//printf("while loops begins in packet function\n");
			
//printf("lamba mu P value are assigned to packet\n");
pthread_mutex_lock(&mutex);
printtime();
fprintf(stdout,"p%d arrives, needs %d tokens, inter-arrival time = %.3Lfms\n",(int)packet->packetid,(int)packet->nooftokensneeded,packet->packetrate/1000);
pthread_mutex_unlock(&mutex);
	
			if(packet->nooftokensneeded <= tokenbuffer->bucketsize)
			{	
				pthread_mutex_lock(&mutex);
				packetbuffer->totalnoofpackets++;
//printf("packet generated and total no of packets in packetbuffer %Lf \n",packetbuffer->totalnoofpackets);
				My402ListAppend(Queue1, packet);//append packet to queue1;
gettimeofday(&(packet->Q1start),NULL); 	//timestamp packetstart in q1
printtime();
fprintf(stdout,"p%d enters Q1\n",(int)packet->packetid);
				
//pthread_mutex_lock(&mutex);

//pthread_mutex_unlock(&mutex);
				pthread_mutex_unlock(&mutex);
				if(My402ListEmpty(Queue1)==0)
				{
					Q1head = My402ListFirst(Queue1);
					if(tokenbuffer->totalnooftokens >=((packettype*)Q1head->obj)->nooftokensneeded)
					{
						pthread_mutex_lock(&mutex);
						temppacket=(packettype*)Q1head->obj;
						My402ListUnlink(Queue1, Q1head);//unlink packet from Q1
						printtime();						
						gettimeofday(&temppacket->Q1end,NULL);//timestamp packetend in q1

//printf("q1head is unlinked with packet id %Lf \n",temppacket->packetid);
durationpacket=(temppacket->Q1end.tv_sec*1000000+temppacket->Q1end.tv_usec)-(temppacket->Q1start.tv_sec*1000000+temppacket->Q1start.tv_usec);
//printf("|||||||time spend in queue1 is %Lf\n",durationpacket);//timestamp
tokenbuffer->totalnooftokens = tokenbuffer->totalnooftokens - ((packettype*)Q1head->obj)->nooftokensneeded;
//pthread_mutex_lock(&mutex);

fprintf(stdout,"p%d leaves Q1, time in Q1 = %.3Lfms, token bucket now has %d tokens\n",(int)temppacket->packetid,durationpacket/1000,(int)tokenbuffer->totalnooftokens);
//pthread_mutex_unlock(&mutex);

avgpacketq1=avgpacketq1+durationpacket;
						
						My402ListAppend(Queue2, Q1head->obj);//append packet to queue2
						gettimeofday(&(((packettype*)Q1head->obj)->Q2start),NULL);//timestamp packetstart in q2
//printf("q1head is appended to queue2 with packet id %Lf \n",((packettype*)Q1head->obj)->packetid);
//pthread_mutex_lock(&mutex);
printtime();
fprintf(stdout,"p%d enters Q2\n",(int)temppacket->packetid);
//pthread_mutex_unlock(&mutex);						//timestamp servertime
//printf("signalserver in packet function\n");
						pthread_cond_signal( &signalserver );
						pthread_mutex_unlock(&mutex);
					}
					else
					{
						//pthread_cond_wait( &waitfortokens, &mutex );
					}
				}
			}
			else
			{
				packetbuffer->noofdroppedpackets++;
				packetbuffer->allpackets--;
				temp--;
pthread_mutex_lock(&mutex);
printtime();
fprintf(stdout,"packet p%d arrives, needs %d tokens, dropped\n",(int)packet->packetid,(int)packet->nooftokensneeded);
pthread_mutex_unlock(&mutex);
			}
			gettimeofday(&end,NULL);
			duration=(end.tv_sec*1000000+end.tv_usec)-(start.tv_sec*1000000+start.tv_usec);
		//	printf("in packet function @@@@duration@@@@ %Lf \n",duration);
				//printf("time in @@@@sleep@@@@ packet%Lf \n",((1/(packet->packetrate))*1000000)-duration);
			
//sleep(3);
		}
	}
stopserver=1;
//printf("Existing packet thread function\n");
return 0;
}
void *serverfunction()
{
	//printf("Enterring server thread function\n");
	struct timeval start,end, startsleep, endsleep; long double timeinsleep;
	long double duration,durationsleep,durationpacket,durationboth,durationtotal; packettype *temppacket;
	My402ListElem *Q2head= malloc(sizeof(My402ListElem));
	//printf("in serverfunction waiting for signal\n");
	//while(stopserver!=1 && !My402ListEmpty(Queue2))

	while(stopserver!=1 || !My402ListEmpty(Queue2) || stoptoken!=1)
	{
		//printf("while loop begins in server function\n");
		if(My402ListEmpty(Queue2)!=0)
		{
			pthread_mutex_lock(&mutex);
			pthread_cond_wait( &signalserver, &mutex );
			pthread_mutex_unlock(&mutex);
		}	//while(noofservedpackets < packetbuffer->allpackets)
	

		//printf("noofservedpacketsin serverwhile %Lf and allpackets %Lf\n",noofservedpackets,packetbuffer->allpackets);
		

		else if(My402ListEmpty(Queue2)==0)
		{
			pthread_mutex_lock(&mutex);
			Q2head = My402ListFirst(Queue2);
			temppacket=(packettype*)Q2head->obj;
			My402ListUnlink(Queue2, Q2head);//remove head of the queue;
			gettimeofday(&(temppacket->Q2end),NULL);//timestamp packetend in q2
			gettimeofday(&start,NULL);
			durationpacket=(temppacket->Q2end.tv_sec*1000000+temppacket->Q2end.tv_usec)-(temppacket->Q2start.tv_sec*1000000+temppacket->Q2start.tv_usec);
			avgpacketq2=avgpacketq2+durationpacket;
			pthread_mutex_unlock(&mutex);
			//printf("removed head of queue2 with packetid %Lf\n",temppacket->packetid);
			//	printf("||||||||time spend in queue2 is%Lf\n",durationpacket);//timestamp
			//printf("||||||||total time spend in queue1 and queue2 is%Lf\n",durationboth);//timestamp
			gettimeofday(&end,NULL);
			duration=(end.tv_sec*1000000+end.tv_usec)-(start.tv_sec*1000000+start.tv_usec);
			//printf("in server function ~~~duration~~~~ %Lf \n",duration);
			//printf("time in ~~~~sleep~~~~ packet%Lf \n",(((1/(((packettype*)Q2head->obj)->servicetime))*1000000)-duration));
			pthread_mutex_lock(&mutex);
			printtime();
			fprintf(stdout,"p%d begin service at S, time in Q2 = %.3Lfms\n",(int)temppacket->packetid,(durationpacket)/1000);
			pthread_mutex_unlock(&mutex);
timeinsleep=temppacket->servicetime - duration;
			if(timeinsleep<0)
timeinsleep=0.0000001;
			
			gettimeofday(&startsleep,NULL);
			usleep(timeinsleep);
			
			gettimeofday(&endsleep,NULL);
			durationsleep= (endsleep.tv_sec*1000000+endsleep.tv_usec)-(startsleep.tv_sec*1000000+startsleep.tv_usec);
			//printf("SLEEP ~~~~DURATION~~~~ server%Lf\n",durationsleep);

			pthread_mutex_lock(&mutex);
			avgservicerate=avgservicerate+(durationsleep+duration);
			durationboth=(temppacket->Q2end.tv_sec*1000000+temppacket->Q2end.tv_usec)-(temppacket->Q1start.tv_sec*1000000+temppacket->Q1start.tv_usec);
			durationtotal=(endsleep.tv_sec*1000000+endsleep.tv_usec)-(temppacket->Q1start.tv_sec*1000000+temppacket->Q1start.tv_usec);
			avgpacketboth=avgpacketboth+durationboth;
			avgpackettotal=avgpackettotal+durationtotal;
			squaredurationtotal= squaredurationtotal+durationtotal*durationtotal;
			//printf("~~~~avgpackettotal%Lf",avgpackettotal);
			//pthread_mutex_lock(&mutex);
			printtime();
			fprintf(stdout,"p%d departs from S, service time = %.3Lfms, time in system = %.3Lfms\n",(int)temppacket->packetid,(durationsleep+duration)/1000,durationtotal/1000);
			//pthread_mutex_unlock(&mutex);
			noofservedpackets++;
			//printf("noofserved packets in server function is %Lf\n",noofservedpackets);
			pthread_mutex_unlock(&mutex);
			
		}

	}
	//printf("Existing server thread function\n");
	return 0;
}

int main(int argc, char *argv[])
{
//printf("main starting point \n");
	struct timeval end;
	long double duration;
// temps, tempss, tempsss;
	double squareroot;
	struct stat sb;
	gettimeofday(&globalstart,NULL);
	pthread_t tokenthread, packetthread, serverthread;
	int rett,retp,rets; char* nott="";
	char *yest="";
	
	FILE *fp=NULL; char *tsfile=NULL;
	int i;  char noofpackets[100];
	tsfile= malloc(sizeof(char));
	

	for(i=1;i<argc;i++)
	{
		if (strcmp(argv[i], "-lambda") == 0)
		{
			if(argv[i+1]==NULL)
			{
				fprintf(stderr,"Error: one more argument needed\n\n");
				exit(1);
			}
			lambda=strtold(argv[i+1],NULL);  
			if(lambda<0)
			{
				fprintf(stderr,"Error: Value of lambda is negative\n\n");
				exit(1);
			}
			if(lambda<=0.1)
				lambda=0.1;			
		}		    
		if (strcmp(argv[i], "-mu") == 0)
		{
			if(argv[i+1]==NULL)
			{
				fprintf(stderr,"Error: one more argument needed\n\n");
				exit(1);
			}
			mu=strtold(argv[i+1],NULL);		
			if(mu<0)
			{
				fprintf(stderr,"Error: Value of mu is negative\n\n");
				exit(1);
			}
			if(mu<=0.1)
				mu=0.1;			
		}							
		if (strcmp(argv[i], "-r") == 0)
		{
			if(argv[i+1]==NULL)
			{
				fprintf(stderr,"Error: one more argument needed\n\n");
				exit(1);
			}
			r=strtold(argv[i+1],NULL);
			if(r<0)
			{
				fprintf(stderr,"Error: Value of r is negative\n\n");
				exit(1);
			}
			if(r<=0.1)
				r=0.1;			
		}		
		if (strcmp(argv[i], "-B") == 0)
		{
			if(argv[i+1]==NULL)
			{
				fprintf(stderr,"Error: one more argument needed\n\n");
				exit(1);
			}
			B=strtold(argv[i+1],NULL);
			if((B<=0 || B>2147483647) || ((int)B!=B))
			{
				fprintf(stderr,"Error: Value of B is not correct\n\n");
				exit(1);
			}
		}
		if (strcmp(argv[i], "-P") == 0)
		{
			if(argv[i+1]==NULL)
			{
				fprintf(stderr,"Error: one more argument needed\n\n");
				exit(1);
			}
			P=strtold(argv[i+1],NULL);
			if((P<=0 || P>2147483647) || ((int)P!=P))
			{
				fprintf(stderr,"Error: Value of P is not correct\n\n");
				exit(1);
			}
		}
		if (strcmp(argv[i], "-n") == 0)
		{	
			if(argv[i+1]==NULL)
			{
				fprintf(stderr,"Error: one more argument needed\n\n");
				exit(1);
			}
			num=strtold(argv[i+1],NULL);
			if((num<=0 || num>2147483647) || ((int)num!=num))
			{
				fprintf(stderr,"Error: Value of num is not correct\n\n");
				exit(1);
			}
		}		
		if (strcmp(argv[i], "-t") == 0)
		{
			if(argv[i+1]==NULL)
			{
				fprintf(stderr,"Error: one more argument needed\n\n");
				exit(1);
			}
			tsfile=argv[i+1]; 
			stat(argv[i+1], &sb);
			if(argv[i+1][0]=='-')
			{
				fprintf(stderr,"Incorrect:Bad Filename Not a command\n");
				exit(1);
			}
			
		}  
		//printf("command line arguments processed%s\n",tsfile);
	}
	//printf("tsfile is here%s and length is %zu",tsfile, strlen(tsfile));
	fprintf(stdout,"\nEmulation Parameters:\n");
	if(strlen(tsfile)==0)
	{
		fprintf(stdout,"    lambda = %-19.3Lf %24s\n",lambda,nott);
		fprintf(stdout,"    mu = %-23.3Lf %24s\n",mu,nott); 
	}
		fprintf(stdout,"    r = %-24.3Lf %24s\n",r, nott);
		fprintf(stdout,"    B = %-24d %24s\n",(int)B,nott);

	if(strlen(tsfile)==0)
	{
		fprintf(stdout,"    P = %-24d %24s\n",(int)P,nott); 
		fprintf(stdout,"    number to arrive = %-9d %24s\n",(int)num,nott); 
	}

	if(strlen(tsfile)!=0)
	{   //printf("fp contains not null value%p\n ",fp);
		fp = fopen(tsfile, "r");
	    //FILE *fpp= fp;
		if(errno==2)
		{
			fprintf(stderr,"Incorrect:File does not exist\n");
			exit(1);
		}
		if(errno==13)
		{
			fprintf(stderr,"Incorrect:File Access denied\n");
			exit(1);
		}
/*code referred from "http://stackoverflow.com/questions/1542763/what-is-the-correct-way-to-use-the-stat-function-to-test-if-a-dirent-is-a-dir" */
/*start from here*/
		
		if( S_ISDIR(sb.st_mode))
/*finish here*/
		{
			fprintf(stderr,"Incorrect:It is a directory\n");
			exit(1);
		}
		if (fp == NULL) 
		{
			fprintf(stderr, "Cannot open file for reading.\n");
			exit(1);
		}
		if (fp!=NULL)
		{
	    //printf("fp contains not null value%p\n ",fp);
		fgets(noofpackets, sizeof(noofpackets), fp);
		num=strtold(noofpackets,NULL);
		//printf("    number to arrive = %-9d %24s\n",(int)num,yest);
		fprintf(stdout,"    tsfile = %-19s %24s\n",tsfile,yest);
		}
		else
		{
			fprintf(stderr,"Error: file do not exist\n");
			exit(1);
		}
	}
	fprintf(stdout,"\n");
    curtime(globalstart);
	fprintf(stdout,"emulation begins\n");
	initializevalue();
	//printf("initialzevalue function returns\n");
	retp = pthread_create( &packetthread, NULL, packetfunction, (void*)fp);
	rett = pthread_create( &tokenthread, NULL, tokenfunction, NULL);
	rets = pthread_create( &serverthread, NULL, serverfunction, NULL);
	if(rett==-1 || retp==-1 || rets==-1)
	{
		fprintf(stderr,"Error creating thread\n");
		exit(1);
	}

	pthread_join( packetthread, NULL);
	pthread_join( serverthread, NULL);
	pthread_join( tokenthread, NULL);

	if(fp!=NULL && (strlen(tsfile)!=0))
	fclose(fp);
	//printf("\nall thread are finished and file is closed\n");

	gettimeofday(&end,NULL);
	duration=(end.tv_sec*1000000+end.tv_usec)-(globalstart.tv_sec*1000000+globalstart.tv_usec);
	
	fprintf(stdout,"\nStatistics:\n\n");
	fprintf(stdout,"    average packet inter-arrival time = %.6Lfs\n",(avgpacketrate/num)/1000000);
	fprintf(stdout,"    average packet service time = %.6Lfs\n",(avgservicerate/num/1000000));
	fprintf(stdout,"    average number of packets in Q1 = %g\n",(double)(avgpacketq1/duration));


//fprintf(stdout,"    avgpacketq1 = %Lf duration=%Lf\n",avgpacketq1,duration);


	fprintf(stdout,"    average number of packets in Q2 = %Lf\n",(avgpacketq2/duration));
	fprintf(stdout,"    average number of packets at S = %Lf\n",(avgservicerate/duration));
if(num!=packetbuffer->noofdroppedpackets)
{
	fprintf(stdout,"    average time a packet spent in system = %.6Lfs\n",(avgpackettotal/(num-packetbuffer->noofdroppedpackets))/1000000);
squareroot= sqrt((squaredurationtotal/(num-packetbuffer->noofdroppedpackets))-(avgpackettotal/(num-packetbuffer->noofdroppedpackets))*(avgpackettotal/(num-packetbuffer->noofdroppedpackets)));

	fprintf(stdout,"    standard deviation for time spent in system = %.6fs\n",squareroot/1000000);
}

if(num==packetbuffer->noofdroppedpackets)
{
	fprintf(stderr,"    average time a packet spent in system = Divide by zero error\n");
fprintf(stderr,"    standard deviation for time spent in system = Divide by zero error\n");
}
//temps= num-packetbuffer->noofdroppedpackets;
//printf("temps total packets processed %Lf\n",temps);
//tempss= squaredurationtotal*squaredurationtotal/temps;
//printf("first value in sd %Lf\n",tempss);
//tempsss=squaredurationtotal/temps;
//printf("second value in sd %Lf\n",tempsss);
	
//printf("total dropped %Lf, total tokens %Lf",tokenbuffer->noofdroppedtokens. );
	fprintf(stdout,"    token drop probability = %Lf\n",tokenbuffer->noofdroppedtokens/(tid));
	fprintf(stdout,"    packet drop probability = %Lf\n\n",packetbuffer->noofdroppedpackets/(pid));


 	
//printf("\nin main function duration%Lf \n",duration);
	return 0;
}



//printf("tokenbuffer->tokenrate%Lf\n" , tokenbuffer->tokenrate);
//printf("tokenbuffer->tokenrate%Lf\n" , (1/(tokenbuffer->tokenrate)));
//printf("tokenbuffer->tokenrate%Lf\n" ,(1/(tokenbuffer->tokenrate))*1000000);





