import socket
print(socket.gethostname())
HOST = "5V8Y7H2"
HOST = str(socket.gethostname())
PORT = 8855
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
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
