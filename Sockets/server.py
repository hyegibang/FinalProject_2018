import socket
HOST = '192.168.33.217'
PORT = 13379
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind((HOST, PORT))
s.listen(1)
connection, address = s.accept()
print('connected by ', address)
while True:

    data = connection.recv(1024)
    print(data)
    if not data: break
connection.close()
