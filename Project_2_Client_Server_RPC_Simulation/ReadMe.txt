This project must be done individually. There will be no exceptions. The purpose of this
project is to develop a client/server version of Project 1, a simulation of Remote
Procedure Call communication (RPC).

The server will be multithreaded. The main server’s thread takes care of the connection
requests (establish rendezvous). The spawned server-threads will carry out the
extended rendezvous with the clients. Most of the (already) implemented code will be
on the server site.

On the client side, you will create the different types of client threads (students,
instructor, timer) that will execute concurrently. If you want you can modify your code
and eliminate the timer. These clients will ask the main server’s thread to establish a
connection.

When the connection is accepted by the server, the main server will create another
“client helper” thread that will carry out the two-way communication with the client
thread. The client will ask the corresponding “client helper” thread to execute
sequentially the methods that were implemented in Project 1 as part of the run method.
Before each method can be executed, it will send the server a message containing its
name and the method name/number to be executed. This can be implemented in
different ways. One way (but not the only way) would be to use a switch-case
structure. This is similar to the process of creating stubs in the client and server sites