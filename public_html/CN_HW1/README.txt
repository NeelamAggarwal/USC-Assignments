
NAME: NEELAM AGGARWAL
USC ID: 6667974120

I did socket programming in c language and made TCP and UDP connections for communication.

Code Files:
1) directory_server
In this file servers(clients) connects to the directory server(server) through UDP and send the directory server their TCP port. Directory server create the file where port no are stored corresponding to the file servers.

2)file_server1
This send the port no to directory server by connecting with the directory server through UDP.  and then process the request of the client and returns the file name requested by the client.

3)file_server2
do the same but send the port no corresponding to the fileserver2

4)file_server3
do the same but send the port no corresponding to the fileserver3

5)client1
This send request to the directory server to look up for the particular file by connecting through TCP. and then send request to the particular client to obtain the file. (actually file name here).

5)client2
do the same but send request for different file.

6)makefile
This file compiles all the files and cleans up too.

How to run:

Step1) Open a terminal -> cd to the directory -> type "make" -> then type "./directory_server".
Step2) Open a new terminal -> cd to the directory -> type "./file_server1".
Step3) Open a new terminal -> cd to the directory -> type "./file_server2".
Step4) Open a new terminal -> cd to the directory -> type "./file_server3".
Step5) Open a new terminal -> cd to the directory -> type "./client1".
Step6) Open a new terminal -> cd to the directory -> type "./client2".
Step7) type "make clean". // All object files will be removed.

Format of all messages exchanged:
1) directory_server
nunki.usc.edu(5): ./directoryserver
Phase1: The Directory Server has UDP port number 21120 and IP address 68.181.201.3.
Phase1: The Directory Server has received request from File Server#1.
Phase1: The Directory Server has received request from File Server#2.
Phase1: The Directory Server has received request from File Server#3.
Phase1: The directory.txt file has been created.
Phase1: End of Phase1 for the Directory Server.
Phase2: The Directory Server has UDP port number 31120 and IP address 68.181.201.3.
Phase2: The Directory Server has received request from Client#1.
Phase2: File Server details has been sent to Client#1.
Phase2: The Directory Server has received request from Client#2.
Phase2: File Server details has been sent to Client#2.
Phase2: End of Phase 2 for the Directory Server.

2) file_server1
nunki.usc.edu(3): ./fileserver1
Phase1: The File Server 1 has UDP port number 22120 and IP address 68.181.201.3.
Phase1: The Registration request from File Server 1 has been sent to the Directory Server.
Phase1: End of Phase 1 for File Server 1.
Phase3: File Server 1 has TCP port 41120 and IP address 68.181.201.3.
Phase3: File Server 1 received the request from the Client1 for the file doc1.
Phase3: File Server 1 has sent doc1 to Client1.

2) file_server2
nunki.usc.edu(3): ./fileserver2
Phase1: The File Server 2 has UDP port number 23120 and IP address 68.181.201.3.
Phase1: The Registration request from File Server 2 has been sent to the Directory Server.
Phase1: End of Phase 1 for File Server 2.
Phase3: File Server 2 has TCP port 42120 and IP address 68.181.201.3.
Phase3: File Server 2 received the request from the Client2 for the file doc2.
Phase3: File Server 2 has sent doc2 to Client2.

2) file_server3
nunki.usc.edu(3): ./fileserver3
Phase1: The File Server 3 has UDP port number 24120 and IP address 68.181.201.3.
Phase1: The Registration request from File Server 3 has been sent to the Directory Server.
Phase1: End of Phase 1 for File Server 3.
Phase3: File Server 3 has TCP port 43120 and IP address 68.181.201.3.

3) client1
nunki.usc.edu(3): ./client1
Phase2: Client 1 has UDP port number 32120 and IP address 68.181.201.3.
Phase2: The File request from Client 1 has been sent to the Directory Server.
Phase2: The File requested by Client 1 is present in File_Server1 and the File Server's TCP port number is 41120.
Phase2: End of Phase 2 for Client 1.
Phase3: Client 1 has dynamic TCP port number 44008 and IP address 68.181.201.3.
Phase3: The File request from Client 1 has been sent to the File_Server1.
Phase3: Client 1 received doc1 from File_Server1.
Phase3: End of Phase 3 for Client 1.
nunki.usc.edu(4):

4) client2
nunki.usc.edu(8): ./client2
Phase2: Client 2 has UDP port number 33120 and IP address 68.181.201.3.
Phase2: The File request from Client 2 has been sent to the Directory Server.
Phase2: The File requested by Client 2 is present in File_Server2 and the File Server's TCP port number is 42120.
Phase2: End of Phase 2 for Client 2.
Phase3: Client 2 has dynamic TCP port number 44009 and IP address 68.181.201.3.
Phase3: The File request from Client 2 has been sent to the File_Server2.
Phase3: Client 2 received doc2 from File_Server2.
Phase3: End of Phase 3 for Client 2.
nunki.usc.edu(4):

----------------------------------
NOTE: dynamic TCP port number can vary.
Program fails when not followed as by the project description and above steps and input should be of exact format as given(not a single extra space is allowed). I assume user will terminate processes at the end by pressing ctrl+C. (for file_server1 , file_server2 , file_server3)

Reuse code: I have used code from the beej tutorial as mentioned and my file_server2 and file_server3 reusing the code from file_server3. And client2 is reusing the code from client1. (As they are suppose to be on different machine as clients). 

******************************************THE END*****************************************
