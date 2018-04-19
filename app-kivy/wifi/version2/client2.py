import socket

host = '192.168.33.217'
port = 54545

clientsocket = socket.socket()
clientsocket.connect((host, port))
print("connected!")

while True:
    text = str(clientsocket.recv(1024), 'utf-8')
