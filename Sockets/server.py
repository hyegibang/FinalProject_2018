import socket
<<<<<<< HEAD
HOST = '0.0.0.0'
PORT = 8855
print(socket.gethostname())
=======
HOST = '192.168.33.217'
PORT = 13379
>>>>>>> 35722ac0407069c3cda0b3b3a03a6ef2c34a359e
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind((socket.gethostbyname('0.0.0.0'), PORT))
s.listen(1)
connection, address = s.accept()
print('connected by ', address)
while True:

    data = connection.recv(1024)
    print(data)
    if not data: break
connection.close()
