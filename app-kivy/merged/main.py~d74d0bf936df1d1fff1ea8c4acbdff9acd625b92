import kivy
kivy.require('1.9.0')
from kivy.app import App
from kivy.lang import Builder
from kivy.uix.button import Button
from kivy.uix.boxlayout import BoxLayout
from kivy.uix.screenmanager import ScreenManager, Screen
from pynput.keyboard import Key, Controller
import time
import os
import socket

# You can create your kv code in the Python file
Builder.load_string("""

<ScreenOne>:
    GridLayout:
        cols:3
        row: 3
        spacing: 20
        padding: [20,10]
        size_hint: 1, 1
        Button:
            text: "line"
            on_press:
                # You can define the duration of the change
                # and the direction of the slide
                #root.manager.transition.direction = 'left'
                #root.manager.transition.duration = 0.01
                #root.manager.current = 'screen_two'
                value = 1
                root.send_data(value)
        Button:
            text: "circle"
            on_press:
                value = 'l'
                root.send_data(value)
        Button:
            text: "spline"
            on_press:
                value = 3
                root.send_data(value)

        Button:
            text: "ellipse"
            on_press:
                value = 4
                root.send_data(value)

        Button:
            text: "arc"
            on_press:
                value = 5
                root.send_data(value)

        Button:
            text: "rectangle"
            on_press:
                value= 6
                root.send_data(value)

<ScreenTwo>:
    GridLayout
        Button:
            text: "Go to Screen 1"
            on_press:
                root.manager.transition.direction = 'right'
                root.manager.current = 'screen_one'
""")

# Create a class for all screens in which you can include
# helpful methods specific to that screen
class ScreenOne(Screen):

    def __init__(self, **kwargs):
        super(ScreenOne, self).__init__(**kwargs)
        host="JZ8Y7H2"
        port=8855
        self.s=socket.socket()
        self.s.connect((host, port))

    def send_data(self,value):
        while True:
            print("hi")
            print(value)
            msg=str(value)
            encoded_msg=bytes(msg, "utf-8")
            self.s.send(encoded_msg)
            print("hi2")

            break

class ScreenTwo(Screen):
    pass

# The ScreenManager controls moving between screens
screen_manager = ScreenManager()

# Add the screens to the manager and then supply a name
# that is used to switch screens
screen_manager.add_widget(ScreenOne(name="screen_one"))
screen_manager.add_widget(ScreenTwo(name="screen_two"))

keyboard = Controller();
# Create a text file for manual code referencing

keyboard = Controller();
# Create a text file for manual code referencing

class MyApp(App):

    def build(self):
        return screen_manager

if __name__ == '__main__':
    sample_app = MyApp()
    sample_app.run()
