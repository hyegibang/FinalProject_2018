"""
Jonathan Zerez, Hyegi Bang
Softdes, Spring 2018
"""

import socket

def start_server(PORT = 6969):
    """
    This script sets up a server socket running on the local machine.
    It returns the connection info of the first machine that connects with it.
    """
    #Initialize the host to the local machine.
    HOST = '0.0.0.0'
    print("Server running on:" + socket.gethostname())
    #Use TCP connections
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    #Create the server
    s.bind((socket.gethostbyname('0.0.0.0'), PORT))
    s.listen(1)

    #Get the connection info for the first device to ping the server
    connection, address = s.accept()
    print('connected by ', address)
    return connection, address

if __name__ == '__main__':
    connection, address = start_server()
    while True:
        data = connection.recv(1024)
        print(data)
        if not data: break
