import socket

host="0.0.0.0"
port=8855
print(socket.gethostname())

s=socket.socket()
s.bind((host, port))
s.listen(10)
c, addr=s.accept()
print("\nconnection successful with "+str(addr)+"\n\n")

def server():
    while True:
        data=c.recv(1024)
        decoded_data=data.decode("utf-8")
        if not decoded_data:
            print("connection with "+ str(addr)+ " broken\n")
        else:
            print("-> "+ decoded_data + "\n")

while True:
    server()
