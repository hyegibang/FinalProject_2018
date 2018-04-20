import socket

def start_server(PORT = 6969):
    HOST = '0.0.0.0'
    print(socket.gethostname())
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

    #s.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    s.bind((socket.gethostbyname('0.0.0.0'), PORT))
    s.listen(1)
    connection, address = s.accept()
    print('connected by ', address)
    return connection, address

if __name__ == '__main__':
    connection, address = start_server()
    while True:
        data = connection.recv(1024)
        print(data)
        if not data: break
