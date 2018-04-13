import socket
import sys
import time
from shortcuts import keystroke

UDP_IP = "192.168.34.203"
UDP_PORT = 23681

sock = socket.socket(socket.AF_INET, # Internet
                     socket.SOCK_DGRAM) # UDP
sock.bind((UDP_IP, UDP_PORT))
empty_responses = 0
init = True
init_pos = [0,0,0]
degree_steps = 5
tilt_threshold = 5
while True:
    data, addr = sock.recvfrom(2048) # buffer size is 1024 bytes
    if sys.getsizeof(data) == 0:
        empty_responses += 1
        print(empty_responses)
    else:
        data = str(data)[2: -1]
        orientation = data.split(' 81, ')
        if len(orientation) > 1:
            commands = []
            orientation_split = [float(elem) for elem in orientation[1].split(',')]
            if orientation_split[1] >= tilt_threshold:
                commands.append("up")
            elif orientation_split[1] <= -tilt_threshold:
                commands.append("down")
            if orientation_split[2] >= tilt_threshold:
                commands.append("right")
            elif orientation_split[2] <= -tilt_threshold:
                commands.append("left")
            keystroke(commands)
    if empty_responses > 100:
        break
