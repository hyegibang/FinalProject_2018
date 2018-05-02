"""
Jonathan Zerez, Hyegi Bang
Software Design, Spring 2018

This is the main script that handles the server and client, as well as
interactions with solidworks. It parses data streaming from the
phone, updates the current solidworks model view and activates shortcuts
appropriately by emulating keyboard presses. It also tracks the state of the
window open and sends that information to the phone.
"""

import socket
import sys
import time
from shortcuts import keystroke
from window import get_current_window
sys.path.insert(0, '../Sockets')
from server import start_server
from client import start_client

PORT = input("Input desired port number (Must be from 5000-65535):")
try:
    PORT = int(PORT)
except:
    print("Invalid port number. Shutting Down.")
    time.sleep(5)
    sys.exit()


#Degree/Rotation Constants
degree_steps = 5
threshold = 15
#Strings to keep track of the state of the file open, and how/when it changes
current_name = None
current_type = "SLDASM"
#A constant that divides the orientation and button data in the data stream
split_key = "button: "

#Start the connections
connection, address = start_server(PORT)
start_client(address[0], "Connection Established", PORT)

while True:
    #Obtain the info about the current foreground window
    window_info = get_current_window()
    print(window_info)
    #If the window is in a solidworks file
    if window_info != "Invalid window type":
        commands = []
        data = connection.recv(1024)
        data = str(data)[2: -1]
        #Extracting the orientaiton part of the stream
        orientation = data
        #Filter out opening transmission confirmation
        if "Hello World" not in orientation and split_key not in orientation:
            #split apart roll and raw from the orientation data
            orientation_split = orientation.split(',')
            try:
                orientation_split = [float(pos) for pos in orientation_split]
                pitch = orientation_split[0]
                roll = orientation_split[1]
                #update the command list appropriately based on orientation
                if pitch > threshold:
                    commands.append("up")
                elif pitch < -threshold:
                    commands.append("down")
                if roll > threshold:
                    commands.append("left")
                elif roll < -threshold:
                    commands.append("right")
                print("rotating")
            except:
                pass

        if split_key in data:
            #Look at the portion of the datastream corresponding to the button press
            keys = data.split(split_key)[-1].split(',')
            #Add the key press to the command list
            for key in keys:
                print(str(key))
                commands.append(key)

        if current_type != window_info[1]:
            start_client(address[0], window_info[1], PORT)
            current_type = window_info[1]
            print("updating phone mode to: ", window_info[1])


        #input commands as keystrokes
        keystroke(commands)
