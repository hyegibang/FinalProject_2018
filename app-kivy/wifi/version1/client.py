import socket

class MySocket:

    def __init__(self,host="192.168.33.217",port=54545):

        self.sock = socket.socket()
        self.sock.connect((host, port))

    def get_data(self):
        return self.sock.recv(1024)
