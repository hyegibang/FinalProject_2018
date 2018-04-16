import socket
import sys
import time
sys.path.insert(0, '../solidworks')
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
            orientation_split = orientation[1].split(',')
            if init:
                init_pos = [float(pos) for pos in orientation_split]
                init = False
            else:
                dist = [None, None, None]
                for i in range(3):
                    dist[i] = float(orientation_split[i]) - init_pos[i]
                print("phone str: ", orientation_split)
                print("phone: ", [float(elem) for elem in orientation_split])
                print("pos: ", init_pos)
                print("dist: ", dist)
                #print(dist[2], float(orientation_split[2]) - float(init_pos))
                print('\n')
                #if abs(dist[1]) > 120:

                pitch_steps = int(dist[1] // degree_steps)
                if pitch_steps > 0:
                    for i in range(abs(pitch_steps)):
                        commands.append("up")
                        init_pos[1] += degree_steps
                elif pitch_steps < 0:
                    for i in range(abs(pitch_steps)):
                        commands.append("down")
                        init_pos[1] -= degree_steps
                roll_steps = int(dist[2] // degree_steps)
                if roll_steps > 0:
                    for i in range(abs(roll_steps)):
                        commands.append("left")
                        init_pos[2] += degree_steps
                elif roll_steps < 0:
                    for i in range(abs(roll_steps)):
                        commands.append("right")
                        init_pos[2] -= degree_steps
            keystroke(commands)
    if empty_responses > 100:
        break
