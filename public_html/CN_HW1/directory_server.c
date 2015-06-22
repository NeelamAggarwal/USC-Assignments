/*
** listener.c -- a datagram sockets "server" demo
*/

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <netdb.h>
#define swap(x, y) do {int tmp; tmp = x; x = y; y = tmp; } while (0)
#define DIRUDP1 "21120"	// the port users will be connecting to
#define DIRUDP2 "31120"
#define SERVERNAME "nunki.usc.edu"
#define MAXBUFLEN 100

void *get_in_addr(struct sockaddr *sa)
{
	if (sa->sa_family == AF_INET) {
		return &(((struct sockaddr_in*)sa)->sin_addr);
	}

	return &(((struct sockaddr_in6*)sa)->sin6_addr);
}

int findport(int mainvalue)
{
	FILE *fp;
	fp = fopen("directory.txt", "r");
	if (fp == NULL)
		{
			printf("Error opening file!\n");
			exit(1);
		}
	char fileserver1[15];char fileserver2[15];char fileserver3[15];
	int portno;
	int temp;
	int mainport;
	fscanf(fp,"%s %d", &fileserver1, &portno);
	temp= fileserver1[11]-'0';
	if(mainvalue == temp )
		mainport= portno;
	fscanf(fp,"%s %d", &fileserver2, &portno);
	temp= fileserver2[11]-'0';
	if(mainvalue == temp )
		mainport= portno;
	fscanf(fp,"%s %d", &fileserver3, &portno);
	temp= fileserver3[11]-'0';
	if(mainvalue == temp )
		mainport= portno;
	fclose(fp);
	return mainport;
}
int lookup(char *buf, char *pointing )
{
	FILE *fp;
	fp = fopen("topology.txt", "r");
	if (fp == NULL)
		{
			printf("Error opening file!\n");
			exit(1);
		}
	int number;
	number= buf[6]-'0';
	//printf("buf string %s \n", buf);
	//printf("in lookup this should be no %d\n", number);
	//printf("in lookup this should be no %c\n", buf[6]);
	int mainvalue;
	int value1,value2, value3;
	int p1, p2, p3;
	int len = strlen(pointing);
	//printf("in lookup this should be len %d\n", len);
	if(len == 1)
	{
		p1 = *pointing-'0';
		mainvalue=p1;
	}
	if(len == 2)
	{
		p1 = *pointing-'0';
		p2 = *(pointing+1)-'0';
		if(p1>p2)
		{
			p1=*(pointing+1)-'0';
			p2=*pointing-'0';
		}
	//	printf("in lookup p1 %d\n", p1);
	//	printf("in lookup p2 %d\n", p2);
	}
	if(len == 3)
	{
		p1 = *pointing-'0';
		p2 = *(pointing+1)-'0';
		p3 = *(pointing+2)-'0';
		if (p2 < p1) swap(p2, p1);
		if (p3 < p2) swap(p3, p2);
		if (p2 < p1) swap(p2, p1);
		//printf("sortedpointing %d%d%d",p1,p2,p3);

	}
	if(number==1)
	{
		fscanf(fp,"%d %d %d\n", &value1, &value2, &value3);
		if(len==2)
		{
			if(p1==1 && p2 ==2)
			{
				if(value1<value2)
					mainvalue=1;
				else
					mainvalue=2;
			}
			if(p1==1 && p2 == 3)
			{
				if(value1<value3)
					mainvalue=1;
				else
					mainvalue=3;
			}
			if(p1==2 && p2 == 3)
			{
				if(value2<value3)
					mainvalue=2;
				else
					mainvalue=3;
			}
		}
		if(len==3)
		{
			if(p1==1 && p2 ==2 && p3==3)
			{
				if(value1<value2 && value1<value3)
					mainvalue=1;
				else if(value2 < value3 && value2 < value1)
					mainvalue=2;
				else
					mainvalue=3;
			}
			
		}
	}
	if(number==2)
	{
		
		fscanf(fp,"%d %d %d\n", &value1, &value2, &value3);
		fscanf(fp,"%d %d %d\n", &value1, &value2, &value3);

		if(len==2)
		{
			if(p1==1 && p2 ==2)
			{
				if(value1<value2)
					mainvalue=1;
				else
					mainvalue=2;
			}
			if(p1==1 && p2 == 3)
			{
				if(value1<value3)
					mainvalue=1;
				else
					mainvalue=3;
			}
			if(p1==2 && p2 == 3)
			{
				if(value2<value3)
					mainvalue=2;
				else
					mainvalue=3;
			}
		}
		if(len==3)
		{
			if(p1==1 && p2 ==2 && p3==3)
			{
				if(value1<value2 && value1<value3)
					mainvalue=1;
				else if(value2 < value3 && value2 < value1)
					mainvalue=2;
				else
					mainvalue=3;
			}
			
		}
	}
	fclose(fp);
	return mainvalue;
}
void search(char *buf, char*message11)
{
	FILE *fp;
	int count = 3;
	fp = fopen("resource.txt", "r");
	char *noofserver = (char*) malloc(3 *sizeof(char));
	char *pointing ;
	pointing= noofserver;
	 if (fp == NULL)
		{
			printf("Error opening file!\n");
			exit(1);
		}
	 while(count--)
	 {
		 char ch[20]; char doc[6]; char docc[6];
		 int number;
		 fscanf(fp,"%s %d", &ch, &number);
		// printf("ch :%s  number: %d  \n", ch, number);
		  if(number==1)
		  {
			 fscanf(fp,"%s", &doc);
			// printf("docn:%s\n",doc);
			 if((*(buf+11) - '0') == (doc[3] - '0') )
			 {
				 //printf("%c , %c ", *(buf+11), doc[3] );
				 strcpy(noofserver , ch+11);
				
				 // printf(":%c %c \n",  *noofserver , ch[11] );
				   noofserver++;
			 }
		  }
		 if(number==2)
		 {
			 fscanf(fp,"%s %s", &doc, &docc);
			// printf("docn:%s %s \n", doc, docc);
			 if((*(buf+11) - '0') == (doc[3] - '0') )
			 {
				// printf("%c , %c ", *(buf+11), doc[3] );
				  strcpy(noofserver , ch+11);
				  
				  //  printf(":%c %c\n",  *noofserver , ch[11] );
					noofserver++;
			 }
			  if((*(buf+11) - '0') == (docc[3] - '0') )
			 {
				// printf("%c , %c ", *(buf+11), docc[3] );
				  strcpy(noofserver , ch+11);
				 
				  //  printf(":%c %c \n",  *noofserver , ch[11] );
					 noofserver++;
			 }
		 }
	 
	 }
	 *noofserver = '\0';
	 fclose(fp);
	// printf("noofservers_pointing:%s \n", pointing);
	 int mainvalue= lookup(buf, pointing);
	 int portno = findport(mainvalue);
	 char a[2]; char b[15]; char c[30] = "File_Server";
	 sprintf(a, "%d", mainvalue);
	 sprintf(b, "%d", portno);
	// char *message;
	  strcat(c,a);
	  strcat(c," ");
	  strcat(c,b);
	  //message11=c;
	  strcpy(message11,c);
	//  printf("string printed %d %d \n", mainvalue,portno);
	//  printf("string printed %s \n", c);
	 // printf("message11 %s \n", message11);
	  //strcpy(message11,c);
//	 return *c;

}
// get sockaddr, IPv4 or IPv6:


int main(void)
{
	int sockfd1; int count3 =3; 
	char message11[50];
	struct addrinfo hints1, *servinfo1, *p1;
	int rv1;
	int numbytes1;
	struct sockaddr_storage their_addr1;
	char buf1[MAXBUFLEN];
	socklen_t addr_len1;
	char s1[INET6_ADDRSTRLEN];

	int sockfd; int count2 = 2;
	struct addrinfo hints, *servinfo, *p;
	int rv;
	int numbytes;
	struct sockaddr_storage their_addr;
	char buf[MAXBUFLEN];
	socklen_t addr_len;
	char s[INET6_ADDRSTRLEN];

	struct sockaddr_in gds;
	socklen_t gds_len;
	struct sockaddr_in gds1;
	socklen_t gds1_len;
	if(1)
	{
												memset(&hints, 0, sizeof hints);
												hints.ai_family = AF_UNSPEC; // set to AF_INET to force IPv4
												hints.ai_socktype = SOCK_DGRAM;
												hints.ai_flags = AI_PASSIVE; // use my IP

												if ((rv = getaddrinfo(SERVERNAME, DIRUDP1, &hints, &servinfo)) != 0) {
													fprintf(stderr, "getaddrinfo: %s\n", gai_strerror(rv));
													return 1;
												}
												
												/*printf("directoryserver: getaddrinfo %s\n",
														inet_ntop(hints.ai_family,
															get_in_addr((struct sockaddr *)servinfo->ai_addr),
															s, sizeof s));*/
												// loop through all the results and bind to the first we can
												for(p = servinfo; p != NULL; p = p->ai_next) {
													if ((sockfd = socket(p->ai_family, p->ai_socktype,
															p->ai_protocol)) == -1) {
														perror("directoryserver: socket\n");
														continue;
													}


													if (bind(sockfd, p->ai_addr, p->ai_addrlen) == -1) {
														close(sockfd);
														perror("directoryserver: bind\n");
														continue;
													}

													break;
												}

												if (p == NULL) {
													fprintf(stderr, "directoryserver: failed to bind socket\n");
													return 2;
												}
												 gds_len = sizeof(struct sockaddr);
												  if (getsockname(sockfd, (struct sockaddr *)&gds, &gds_len) == -1) {
													  perror("getsockname() failed\n");
													  return -1;
												   }
												//   printf("directoryserver:Local IP address is: %s\n", inet_ntoa(gds.sin_addr));
												//	printf("directoryserver:Local port is: %d\n", (int) ntohs(gds.sin_port));
								printf("Phase1: The Directory Server has UDP port number %d and IP address %s.\n",(int) ntohs(gds.sin_port),inet_ntoa(gds.sin_addr));

												inet_ntop(p->ai_family, get_in_addr((struct sockaddr *)p->ai_addr),
														s, sizeof s);
											//	printf("directoryserver: local address %s\n", s);

												freeaddrinfo(servinfo);

											//	printf("directoryserver: waiting to recvfrom...\n");

												addr_len = sizeof their_addr;
	FILE *fp;
	//FILE *fpp;
   //char buff[255];
	   fp = fopen("directory.txt", "w");
	   if (fp == NULL)
		{
			printf("Error opening file!\n");
			exit(1);
		}
	    
	//fprintf(fp,"%s" ,"This is testing for fprintf...\n");
	 //  fputs(buf, fp);
												while(count3--)
												{
													if ((numbytes = recvfrom(sockfd, buf, MAXBUFLEN-1 , 0,
														(struct sockaddr *)&their_addr, &addr_len)) == -1) {
														perror("recvfrom\n");
														exit(1);
													}
													buf[numbytes] = '\0';
													printf("Phase1: The Directory Server has received request from File Server#%c.\n", *(buf+11));
													/*printf("directoryserver: got packet from %s\n",
														inet_ntop(their_addr.ss_family,
															get_in_addr((struct sockaddr *)&their_addr),
															s, sizeof s));
													printf("directoryserver:got packet from port is: %d\n", (int) ntohs((struct sockaddr_in *)&their_addr)->sin_port);
													printf("directoryserver: packet is %d bytes long\n", numbytes);*/
													
													//buf[++numbytes] = '\n';
						//printf("directoryserver: packet contains \"%s\"\n", buf);
								 fprintf(fp, "%s\r\n", buf);
	   //fputc('\n', fp);
	 
												}
												 fclose(fp);
												 printf("Phase1: The directory.txt file has been created.\nPhase1: End of Phase1 for the Directory Server.\n");
												/*FILE *fpp;
												 char buff[255];
												 fpp = fopen("directory.txt", "r");*/
												 /*printf("file contains:\n");
												   while(fgets(buff, 255, fpp)!=NULL)
												   {
													 printf( " %s \n", buff);
												   }*/
												/*  fclose(fpp);*/
												close(sockfd);
	}
	 if(1)
	{
						memset(&hints1, 0, sizeof hints1);
						hints1.ai_family = AF_UNSPEC; // set to AF_INET to force IPv4
						hints1.ai_socktype = SOCK_DGRAM;
						//hints.ai_flags = AI_PASSIVE; // use my IP

						if ((rv1 = getaddrinfo(SERVERNAME, DIRUDP2, &hints1, &servinfo1)) != 0) {
							fprintf(stderr, "directoryserver2:getaddrinfo: %s\n", gai_strerror(rv1));
							return 1;
						}

						// loop through all the results and bind to the first we can
						for(p1 = servinfo1; p1 != NULL; p1 = p1->ai_next) {
							if ((sockfd1 = socket(p1->ai_family, p1->ai_socktype,
									p1->ai_protocol)) == -1) {
								perror("directoryserver2: socket\n");
								continue;
							}

							if (bind(sockfd1, p1->ai_addr, p1->ai_addrlen) == -1) {
								close(sockfd1);
								perror("directoryserver2: bind\n");
								continue;
							}

							break;
						}

						if (p1 == NULL) {
							fprintf(stderr, "directoryserver2: failed to bind socket\n");
							return 2;
						}
													gds1_len = sizeof(struct sockaddr);
												  if (getsockname(sockfd1, (struct sockaddr *)&gds1, &gds1_len) == -1) {
													  perror("getsockname() failed\n");
													  return -1;
												   }
												  // printf("directoryserver2:Local IP address is: %s\n", inet_ntoa(gds1.sin_addr));
													//printf("directoryserver2:Local port is: %d\n", (int) ntohs(gds1.sin_port));
							printf("Phase2: The Directory Server has UDP port number %d and IP address %s.\n",(int) ntohs(gds1.sin_port),inet_ntoa(gds1.sin_addr));
												inet_ntop(p1->ai_family, get_in_addr((struct sockaddr *)p1->ai_addr),
														s1, sizeof s1);
												//printf("directoryserver2: local address %s\n", s1);

						freeaddrinfo(servinfo1);

					//	printf("directoryserver2: waiting to recvfrom...\n");

						addr_len1 = sizeof their_addr1;
						while(count2--)
					{
						if ((numbytes1 = recvfrom(sockfd1, buf1, MAXBUFLEN-1 , 0,
							(struct sockaddr *)&their_addr1, &addr_len1)) == -1) {
							perror("directoryserver2: recvfrom\n");
							exit(1);
						}

						buf1[numbytes1] = '\0';
						/*printf("directoryserver2: got packet from %s\n",
							inet_ntop(their_addr1.ss_family,
								get_in_addr((struct sockaddr *)&their_addr1),
								s1, sizeof s1));
						printf("directoryserver2: got packet from port is: %d\n", (int) ntohs((struct sockaddr_in *)&their_addr1)->sin_port);
						printf("directoryserver2: packet is %d bytes long\n", numbytes1);
						
						printf("directoryserver2: packet contains %s \n", buf1);*/
						//*buf= "helo";
						printf("Phase2: The Directory Server has received request from Client#%c.\n",*(buf1+6));
						search(buf1, message11);
						//printf("directoryserver2: after search %s \n",message11);
						if ((numbytes1 = sendto(sockfd1, message11, MAXBUFLEN-1 , 0,
							(struct sockaddr *)&their_addr1, addr_len1)) == -1) {
							perror("directoryserver2: recvfrom\n");
							exit(1);
						}
						printf("Phase2: File Server details has been sent to Client#%c.\n",*(buf1+6));
					}
						printf("Phase2: End of Phase 2 for the Directory Server.\n");
						close(sockfd1);
	}

	return 0;
}
