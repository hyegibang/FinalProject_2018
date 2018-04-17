import socket

class MySocket:

    def __init__(self,host="192.168.33.217",port=54545):

        self.sock = socket.socket()
        self.sock.bind(('', port))

        self.sock.listen(1)

        clientsocket,addr = self.sock.accept()
        print("got a connection from %s" % str(addr))

    def send_data(self):
        clientsocket,addr = self.sock.accept()
        while True:
            msg = input("> ") + "\r\n"
            clientsocket.send(msg.encode('ascii'))
