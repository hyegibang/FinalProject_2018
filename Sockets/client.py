"""
Jonathan Zerez, Hyegi Bang
SofDes Spring 2018
"""

import socket

def start_client(HOST, message, PORT = 6969, verbose = False):
    """
    This script creates a client that pings a specified server with a message.
    It does not return anything
    """
    #Check the connection info for the desired Host
    if verbose:
        print(socket.gethostbyname(HOST))
    #Create the socket
    s = socket.socket()
    print(HOST, PORT)
    s.connect((HOST, PORT))
    if verbose:
        print("connected!")
    #send the message
    s.send(bytes(message, 'utf-8'))

if __name__ == '__main__':
    start_client(HOST = "5V8Y7H2", message = 'hellow world', PORT = 6969)
