import socket

def start_client(HOST, message, PORT = 6969, verbose = False):
    if verbose:
        print(socket.gethostbyname(HOST))
    s = socket.socket()
    print(HOST, PORT)
    s.connect((HOST, PORT))
    if verbose:
        print("connected!")
    s.send(bytes(message, 'utf-8'))

if __name__ == '__main__':
    start_client(HOST = "311Z7H2", message = 'hellow world', PORT = 6969)
