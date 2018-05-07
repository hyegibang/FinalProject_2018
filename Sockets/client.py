"""
Jonathan Zerez, Hyegi Bang
SofDes Spring 2018
"""

import socket

def start_client(HOST, PORT, message, verbose = False):
    """
    This script creates a client that pings a specified server with a message.
    It does not return anything
    """
    #Check the connection info for the desired Host

    #Create the socket
    s = socket.socket()
    if verbose:
        print(socket.gethostbyname(HOST))
        print(HOST, PORT)
    s.connect((HOST, PORT))
    if verbose:
        print("connected!")
    s.send(bytes(message, 'utf-8'))
    s.close()


if __name__ == '__main__':
    start_client(HOST = "5V8Y7H2", message = 'hellow world', PORT = 6969)
