/*
** talker.c -- a datagram "client" demo
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
#define MAXDATASIZE 100 // max number of bytes we can get at once 
#define CUDP "32120"	// the port users will be connecting to
#define DIRUDP2 "31120"
#define SERVERNAME "nunki.usc.edu"

void *get_in_addr(struct sockaddr *sa)
{
	if (sa->sa_family == AF_INET) {
		return &(((struct sockaddr_in*)sa)->sin_addr);
	}

	return &(((struct sockaddr_in6*)sa)->sin6_addr);
}

int main(int argc, char *argv[])
{
	int sockfd1;
	struct addrinfo hints1, *servinfo1, *p1;
	struct addrinfo hints1c, *servinfo1c, *p1c;
	int rv1;
	int rv1c;
	int numbytes1;
	char buf1[20];
	struct sockaddr_storage their_addr1;
	socklen_t addr_len1;
		struct hostent *he;
	struct sockaddr_in gc1;
	socklen_t gc1_len;
	struct sockaddr_in gc;
	socklen_t gc_len;

	int count2 =2; char phase2[20] =  "Client1 doc1"; int phaseidx=0;
	int sockfd, numbytes;  
	char buf[MAXDATASIZE];
	struct addrinfo hints, *servinfo, *p;
	int rv;
	char s[INET6_ADDRSTRLEN];
	char portno[20];
	//udp connection phase 2
	if(1)
	{

						memset(&hints1, 0, sizeof hints1);
						hints1.ai_family = AF_UNSPEC;
						hints1.ai_socktype = SOCK_DGRAM;

						memset(&hints1c, 0, sizeof hints1c);
						hints1c.ai_family = AF_UNSPEC;
						hints1c.ai_socktype = SOCK_DGRAM;

						if ((he = gethostbyname(SERVERNAME)) == NULL) {  // get the host info
														fprintf(stderr, "gethostby name %s\n",he->h_name);
														return 2;
													}

						if ((rv1 = getaddrinfo(SERVERNAME, DIRUDP2, &hints1, &servinfo1)) != 0) {
							fprintf(stderr, "client1: getaddrinfo: %s\n", gai_strerror(rv1));
							return 1;
						}
						
						if ((rv1c = getaddrinfo(SERVERNAME, CUDP, &hints1c, &servinfo1c)) != 0) {
							fprintf(stderr, "client1: getaddrinfo: %s\n", gai_strerror(rv1c));
							return 1;
						}
					/*	printf("client1: getaddrinfo address %s\n",
														inet_ntop(hints1.ai_family,
															get_in_addr((struct sockaddr *)servinfo->ai_addr),
															s, sizeof s));*/
													

						// loop through all the results and make a socket
						for(p1 = servinfo1; p1 != NULL; p1 = p1->ai_next) {
							if ((sockfd1 = socket(p1->ai_family, p1->ai_socktype,
									p1->ai_protocol)) == -1) {
								perror("client1: socket\n");
								continue;
							}
							if (bind(sockfd1, servinfo1c->ai_addr, servinfo1c->ai_addrlen) == -1) {
														close(sockfd1);
														perror("client1: bind\n");
														continue;
													}
							break;
						}
						addr_len1 = sizeof their_addr1;
						if (p1 == NULL) {
							fprintf(stderr, "client1: failed to bind socket\n");
							return 2;
						}
												gc1_len = sizeof(struct sockaddr);
												  if (getsockname(sockfd1, (struct sockaddr *)&gc1, &gc1_len) == -1) {
													  perror("getsockname() failed\n");
													  return -1;
												   }
					printf("Phase2: Client 1 has UDP port number %d and IP address %s.\n",(int) ntohs(gc1.sin_port),inet_ntoa(gc1.sin_addr));
				
							if ((numbytes1 = sendto(sockfd1, phase2, strlen(phase2), 0,
									 p1->ai_addr, p1->ai_addrlen)) == -1) {
								perror("client1: sendto \n");
								exit(1);
							}
							printf("Phase2: The File request from Client 1 has been sent to the Directory Server.\n");

							if ((numbytes1 = recvfrom(sockfd1, buf1, 20 , 0,
								(struct sockaddr *)&their_addr1, &addr_len1)) == -1) {
								perror("client1: recvfrom \n");
								exit(1);
							}
							//printf("client1: ackreceived: %s\n", buf1);
						printf("Phase2: The File requested by Client 1 is present in %-.12s and the File Server's TCP port number is %s.\n",buf1,buf1+13);

						freeaddrinfo(servinfo1);
						printf("Phase2: End of Phase 2 for Client 1.\n");
						//printf("client1: sent %d bytes to %s\n", numbytes1, phase2);
						close(sockfd1);
	}
	//strcpy(buf1, "File_Server1 48888");
	strcpy(portno, buf1+13);
//	printf("client1: tcp portno %s\n", portno);

	//TCP CONNECTION PHASE 3
	 if(1)
	{
															

														memset(&hints, 0, sizeof hints);
														hints.ai_family = AF_UNSPEC;
														hints.ai_socktype = SOCK_STREAM;

														if ((rv = getaddrinfo(SERVERNAME, portno, &hints, &servinfo)) != 0) {
															fprintf(stderr, "client1: getaddrinfo: %s\n", gai_strerror(rv));
															return 1;
														}
														//printf("portno %s\n", portno);
														// loop through all the results and connect to the first we can
														for(p = servinfo; p != NULL; p = p->ai_next) {
															if ((sockfd = socket(p->ai_family, p->ai_socktype,
																	p->ai_protocol)) == -1) {
																perror("client1: socket\n");
																continue;
															}

															if (connect(sockfd, p->ai_addr, p->ai_addrlen) == -1) {
																close(sockfd);
																perror("client1: connect\n");
																continue;
															}

															break;
														}

														if (p == NULL) {
															fprintf(stderr, "client1: failed to connect\n");
															return 2;
														}

														gc_len = sizeof(struct sockaddr);
												  if (getsockname(sockfd, (struct sockaddr *)&gc, &gc_len) == -1) {
													  perror("getsockname() failed\n");
													  return -1;
												   }
					printf("Phase3: Client 1 has dynamic TCP port number %d and IP address %s.\n",(int) ntohs(gc.sin_port),inet_ntoa(gc.sin_addr));

														inet_ntop(p->ai_family, get_in_addr((struct sockaddr *)p->ai_addr),
																s, sizeof s);
														/*printf("client1: connecting to %s\n", s);
										printf("directoryserver:got packet from port is: %d\n", (int) ntohs((struct sockaddr_in *)p->ai_addr)->sin_port);*/
														freeaddrinfo(servinfo); // all done with this structure

															if (send(sockfd, "Client1 doc1", 13, 0) == -1)
															perror("client1: send\n");

														printf("Phase3: The File request from Client 1 has been sent to the %-.12s.\n", buf1);	
														if ((numbytes = recv(sockfd, buf, MAXDATASIZE-1, 0)) == -1) {
															perror("client1: recv\n");
															exit(1);
													
															
															
															//if (send(sockfd, "Client1 doc1", MAXDATASIZE-1, 0) == -1)
															//perror("send");
														}
														buf[numbytes] = '\0';
														printf("Phase3: Client 1 received %s from %-.12s.\n",buf,buf1 );
														

														//printf("client1: received '%s'\n",buf);
														printf("Phase3: End of Phase 3 for Client 1.\n");
														close(sockfd);

														}
	return 0;
}
