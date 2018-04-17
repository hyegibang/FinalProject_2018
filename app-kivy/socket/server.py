import socket

def Main():
    host = '127.0.0.1'
    port = 7000

    mySocket = socket.socket()
    mySocket.bind((host,port))

    mySocket.listen(1)
    conn, addr = mySocket.accept()
    print ("Connection from: " + str(addr))
    message = 'Thank you connecting'
    conn.send(message.encode())

    while True:
        data = conn.recv(1024).decode()
        strdata = str(data)
        print(strdata)
        reply = 'confirmed'
        conn.send(reply.encode())

    mySocket.close()

if __name__ == '__main__':
    Main()
