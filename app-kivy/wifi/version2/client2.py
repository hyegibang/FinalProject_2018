import socket

host = '192.168.33.217'
port = 54545

clientsocket = socket.socket()
clientsocket.connect((host, port))
print("connected!")

while True:
    clientsocket.recv(1024)
    text = str(clientsocket.get_data(), 'utf-8')
    print(text)
