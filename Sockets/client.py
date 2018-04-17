import socket
print(socket.gethostname())
HOST = "LAPTOP-C2CB21R0"
#HOST = "311Z7H2"
print(socket.gethostbyname('192.168.35.245'))
print(socket.gethostbyname(HOST))
PORT = 6969
s = socket.socket()
s.connect((HOST, PORT))
print("connected!")
s.send(bytes("hello world", 'utf-8'))
"""
connection, address = s.accept()
print('connected by ', address)
while True:
    data = conn.recv(1024)
    print(data)
    if not data: break
"""
