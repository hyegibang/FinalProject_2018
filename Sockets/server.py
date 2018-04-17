import socket

HOST = '0.0.0.0'
PORT = 6969
print(socket.gethostname())

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

#s.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
s.bind((socket.gethostbyname('0.0.0.0'), PORT))
s.listen(1)
connection, address = s.accept()
print('connected by ', address)
while True:

    data = connection.recv(1024)
    print(data)
    if not data: break
connection.close()
