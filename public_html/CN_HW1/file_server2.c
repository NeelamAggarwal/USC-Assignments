/*
** talker.c -- a datagram "client" demo
*/

#include <netdb.h>
#include <stdio.h>      /* for printf() and fprintf() */
#include <sys/types.h>  /* for Socket data types */
#include <sys/socket.h> /* for socket(), connect(), send(), and recv() */
#include <netinet/in.h> /* for IP Socket data types */
#include <arpa/inet.h>  /* for sockaddr_in and inet_addr() */
#include <stdlib.h>     /* for atoi() */
#include <string.h>     /* for memset() */
#include <unistd.h>     /* for close() */

#include <stdlib.h>
#include <errno.h>
#include <netdb.h>
#include <sys/wait.h>
#include <signal.h>

#define MAXDATASIZE 100
#define BACKLOG 5	 // how many pending connections queue will hold
#define FSUDP "23120"	// the port users will be connecting to
#define DIRUDP1 "21120"
#define FSTCP "42120"  // the port users will be connecting to
#define SERVERNAME "nunki.usc.edu"
//#define phase1 "File_Server 22120" 
//void sigchld_handler(int s)
//{
//	while(waitpid(-1, NULL, WNOHANG) > 0);
//}
// get sockaddr, IPv4 or IPv6:
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
	int phaseidx=0;
	struct addrinfo hints1, *servinfo1, *p1;
	struct addrinfo hintsfs, *servinfofs, *pfs;
	int rv1;
	int rvfs;
	int numbytes1;
	char buf1[50];
	int count3 =3; char phase1[20] =  "File_Server2 42120";

	int sockfd, new_fd;  // listen on sock_fd, new connection on new_fd
	struct addrinfo hints, *servinfo, *p;
	
	struct sockaddr_storage their_addr; // connector's address information
	socklen_t sin_size;
	struct sigaction sa;
	int yes=1;
	char s[INET6_ADDRSTRLEN];
	int rv;
	char buf[20];
	int numbytes;
	struct sockaddr_in fs;
	socklen_t fs_len;

	struct sockaddr_in gfs;
	socklen_t gfs_len;

	struct sockaddr_in gfs1;
	socklen_t gfs1_len;

	if(1)
	{
					///*if (argc != 2) {
					//	fprintf(stderr,"usage: talker hostname message\n");
					//	exit(1);
					//}*/

					memset(&hints1, 0, sizeof hints1);
					hints1.ai_family = AF_UNSPEC;
					hints1.ai_socktype = SOCK_DGRAM;

					memset(&hintsfs, 0, sizeof hintsfs);
					hintsfs.ai_family = AF_UNSPEC;
					hintsfs.ai_socktype = SOCK_DGRAM;

					if ((rv1 = getaddrinfo(SERVERNAME, DIRUDP1, &hints1, &servinfo1)) != 0) {
						fprintf(stderr, "fileserver2: getaddrinfo: %s\n", gai_strerror(rv1));
						return 1;
					}

					if ((rvfs = getaddrinfo(SERVERNAME, FSUDP, &hintsfs, &servinfofs)) != 0) {
						fprintf(stderr, "fileserver2: getaddrinfo: %s\n", gai_strerror(rvfs));
						return 1;
					}
					// loop through all the results and make a socket
					for(p1 = servinfo1; p1 != NULL; p1 = p1->ai_next) {
						if ((sockfd1 = socket(p1->ai_family, p1->ai_socktype,
								p1->ai_protocol)) == -1) {
							perror("fileserver2: socket\n");
							continue;
						}
						if (bind(sockfd1, servinfofs->ai_addr, servinfofs->ai_addrlen) == -1) {
														close(sockfd1);
														perror("fileserver2: bind\n");
														continue;
													}
						break;
					}


					if (p1 == NULL) {
						fprintf(stderr, "fileserver2: failed to bind socket\n");
						return 2;
					}
													gfs1_len = sizeof(struct sockaddr);
												  if (getsockname(sockfd1, (struct sockaddr *)&gfs1, &gfs1_len) == -1) {
													  perror("fileserver2: getsockname() failed\n");
													  return -1;
												   }
						  printf("Phase1: The File Server 2 has UDP port number %d and IP address %s.\n",(int) ntohs(gfs1.sin_port),inet_ntoa(gfs1.sin_addr));
					//while(count3--)
					{
						
						if ((numbytes = sendto(sockfd1, phase1, strlen(phase1), 0,
								 p1->ai_addr, p1->ai_addrlen)) == -1) {
							perror("fileserver2: sendto\n");
							exit(1);
						}
						printf("Phase1: The Registration request from File Server 2 has been sent to the Directory Server.\nPhase1: End of Phase 1 for File Server 2.\n");
						//phaseidx++;
					}
					freeaddrinfo(servinfo1);

				//	printf("talker: sent %d bytes to %s\n", numbytes, phase1);
					close(sockfd1);
	}
	if(1)
	{
													memset(&hints, 0, sizeof hints);
												hints.ai_family = AF_UNSPEC;
												hints.ai_socktype = SOCK_STREAM;
												hints.ai_flags = AI_PASSIVE; // use my IP

												if ((rv = getaddrinfo(SERVERNAME,FSTCP, &hints, &servinfo)) != 0) {
													fprintf(stderr, "fileserver2: getaddrinfo: %s\n", gai_strerror(rv));
													return 1;
												}

												// loop through all the results and bind to the first we can
												for(p = servinfo; p != NULL; p = p->ai_next) {
													if ((sockfd = socket(p->ai_family, p->ai_socktype,
															p->ai_protocol)) == -1) {
														perror("fileserver2: socket\n");
														continue;
													}

													if (setsockopt(sockfd, SOL_SOCKET, SO_REUSEADDR, &yes,
															sizeof(int)) == -1) {
														perror("fileserver2: setsockopt\n");
														exit(1);
													}

													if (bind(sockfd, p->ai_addr, p->ai_addrlen) == -1) {
														close(sockfd);
														perror("fileserver2: bind\n");
														continue;
													}

													break;
												}

												if (p == NULL)  {
													fprintf(stderr, "fileserver2: failed to bind\n");
													return 2;
												}
												gfs_len = sizeof(struct sockaddr);
												  if (getsockname(sockfd, (struct sockaddr *)&gfs, &gfs_len) == -1) {
													  perror("fileserver2: getsockname() failed\n");
													  return -1;
												   }
							 printf("Phase3: File Server 2 has TCP port %d and IP address %s.\n",(int) ntohs(gfs.sin_port),inet_ntoa(gfs.sin_addr));
												 //  printf("fileserver2: Local IP address is: %s\n", inet_ntoa(gfs.sin_addr));
												//	printf("fileserver2: Local port is: %d\n", (int) ntohs(gfs.sin_port));

												freeaddrinfo(servinfo); // all done with this structure

												if (listen(sockfd, BACKLOG) == -1) {
													perror("fileserver2: listen\n");
													exit(1);
												}

												//sa.sa_handler = sigchld_handler; // reap all dead processes
												//sigemptyset(&sa.sa_mask);
												//sa.sa_flags = SA_RESTART;
												//if (sigaction(SIGCHLD, &sa, NULL) == -1) {
												//	perror("fileserver2: sigaction\n");
												//	exit(1);
												//}

											//	printf("fileserver2: waiting for connections...\n");

												while(1)
												{  // main accept() loop
													sin_size = sizeof their_addr;
													new_fd = accept(sockfd, (struct sockaddr *)&their_addr, &sin_size);
													if (new_fd == -1) {
														perror("fileserver2: accept\n");
														//continue;
													}

													inet_ntop(their_addr.ss_family,
														get_in_addr((struct sockaddr *)&their_addr),
														s, sizeof s);

												//	printf("fileserver2: got connection from %s\n", s);
												//	printf("fileserver2: got connection from port is: %d\n", (int) ntohs((struct sockaddr_in *)&their_addr)->sin_port);

													if (!fork()) { // this is the child process
														close(sockfd); // child doesn't need the listener
														if ((numbytes = recv(new_fd, buf, MAXDATASIZE-1, 0)) == -1) {
														perror("fileserver2: recv\n");
														exit(1);
														}
														printf("Phase3: File Server 2 received the request from the %-.7s for the file %s.\n",buf, buf+8);
														//printf("fileserver2: received packet by fileserver from client %s\n", buf);
														if (send(new_fd, buf+8, 13, 0) == -1)
														perror("fileserver2: send\n");

														printf("Phase3: File Server 2 has sent %s to %-.7s.\n",buf+8, buf);	
													close(new_fd);
														exit(0);
													}
														
												
														/*if ((numbytes = recv(new_fd, buf, MAXDATASIZE-1, 0)) == -1) {
															perror("recv");
															exit(1);*/

														//close(new_fd);  // parent doesn't need this
													}
													
	}
		return 0;
											}
											
											
										
		
	
										
		
	
  
