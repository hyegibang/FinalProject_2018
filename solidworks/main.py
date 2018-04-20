"""
Jonathan Zerez, Hyegi Bang
Software Design, Spring 2018

This is the main script that handles the server and client, as well as
interactions with solidworks.

"""

import socket
import sys
from shortcuts import keystroke
from window import get_current_window
sys.path.insert(0, '../Sockets')
from server import start_server
from client import start_client

PORT = 6969
degree_steps = 5
threshold = 15
verbose = False
current_name = None
current_type = "SLDASM"
split_key = "XYZ"

connection, address = start_server(PORT)
start_client(address[0], "Connection Established", PORT)

while True:

    window_info = get_current_window()
    print(window_info)
    if len(window_info) > 1:
        commands = []
        data = connection.recv(1024)
        data = str(data)[2: -1]
        #Extracting the orientaiton part of the stream
        orientation = data.split(split_key)[0]
        print(orientation)
        if "Hello World" not in orientation and len(data.split(split_key)) == 2:
            orientation_split = orientation.split(',')
            orientation_split = [float(pos) for pos in orientation_split]
            pitch = orientation_split[0]
            roll = orientation_split[1]
            if pitch > threshold:
                commands.append("up")
            elif pitch < -threshold:
                commands.append("down")
            if roll > threshold:
                commands.append("left")
            elif roll < -threshold:
                commands.append("right")
            #input the keystrokes corresponding to the current time step
        """
        keys = data.split(split_key)[-1].split(',')
        for key in keys:
            commands.append(key)

        """

        if current_type != window_info[0]:
            start_client(address[0], window_info[1], PORT)
            current_type = window_info[0]
            print("updating phone mode to: ", window_info[0])
        keystroke(commands)
