import socket
import sys
import time
sys.path.insert(0, '../solidworks')
from shortcuts import keystroke
from window import get_current_window

#Parsing command line inputs
if len(sys.argv) == 1:
    #Default case for developer testing
    UDP_IP = "192.168.34.203"
    UDP_PORT = 23681
elif len(sys.argv) == 3:
    UDP_IP = str(sys.argv[1])
    UDP_PORT = int(sys.argv[2])
else:
    print("invalid number of arguments. please input the IP address followed by the desired port number")
    sys.exit()
print("RUNNING WITH IP: ", UDP_IP)
print("RUNNING ON PORT: ", UDP_PORT)
time.sleep(3)


#Initialize socket
sock = socket.socket(socket.AF_INET, # Internet
                     socket.SOCK_DGRAM) # UDP
sock.bind((UDP_IP, UDP_PORT))

init = True
init_pos = [0,0,0]
degree_steps = 5
while True:
    if get_current_window():
        print("valid")
        #Recieve and slightly reformat input stream
        try:
            data, addr = sock.recvfrom(2048)
        except KeyboardInterrupt:
            break;
        data = str(data)[2: -1]
        #Extracting the orientaiton part of the stream
        orientation = data.split(' 81, ')
        if len(orientation) > 1:
            commands = []
            orientation_split = orientation[1].split(',')
            #Set the initial orientation of the phone as the origin
            if init:
                init_pos = [float(pos) for pos in orientation_split]
                init = False
            else:
                #Calculate the angle moved between timesteps
                dist = [None, None, None]
                for i in range(3):
                    dist[i] = float(orientation_split[i]) - init_pos[i]
                print("phone str: ", orientation_split)
                print("phone: ", [float(elem) for elem in orientation_split])
                print("pos: ", init_pos)
                print("dist: ", dist)
                print('\n')

                #Normalize the distance relative to the step of each degree
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
            #input the keystrokes corresponding to the current time step
            keystroke(commands)
    else:
        print("invalid window")
