all:
	g++ -o directory_server directory_server.c -lsocket -lnsl -lresolv
	g++ -o file_server1 file_server1.c -lsocket -lnsl -lresolv
	g++ -o client1 client1.c -lsocket -lnsl -lresolv
	g++ -o file_server2 file_server2.c -lsocket -lnsl -lresolv
	g++ -o client2 client2.c -lsocket -lnsl -lresolv
	g++ -o file_server3 file_server3.c -lsocket -lnsl -lresolv
clean:
	rm -rf directory_server file_server1 client1 file_server2 client2 file_server3
