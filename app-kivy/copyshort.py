"""
Jonathan Zerez, April 2018
This script provides a function that takes inputs and
processes them as keystrokes

"""
from pynput.keyboard import Key, Controller
import time
import os

keyboard = Controller();
# Create a text file for manual code referencing
if 'keycodes.txt' not in os.listdir():
    keycodes = open('keycodes.txt', 'w')
    for name, member in Key.__members__.items():
        print(name, member)
        keycodes.write(str(name) + ": " + str(member) + '\n')


def keystroke(inputs):
    """
    This function takes a tuple or list of arguments and properly manipulates
    the correct keys. In this way, we can take advantage of preprogramed
    solidworks shortcuts.
    """
    for input in inputs:
        if len(input) == 1:
            keyboard.press(input)
        else:
            keyboard.press(Key[input])
    for input in inputs:
        if len(input) == 1:
            keyboard.release(input)
        else:
            keyboard.release(Key[input])

if __name__ == '__main__':
    keystroke('l')
