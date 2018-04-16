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
                print(value)
        Button:
            text: "circle"
            on_press:
                value = 'l'
                root.keystroke(value)
        Button:
            text: "spline"
            on_press:
                value = 3
                root.keystroke(value)

        Button:
            text: "ellipse"
            on_press:
                value = 4
                root.keystroke(value)

        Button:
            text: "arc"
            on_press:
                value = 5
                root.keystroke(value)

        Button:
            text: "rectangle"
            on_press:
                value= 6
                root.keystroke(value)

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

    def keystroke(self, inputs):
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
        print(inputs)
    pass

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
if 'keycodes.txt' not in os.listdir():
    keycodes = open('keycodes.txt', 'w')
    for name, member in Key.__members__.items():
        print(name, member)
        keycodes.write(str(name) + ": " + str(member) + '\n')


class MyApp(App):

    def build(self):
        return screen_manager

if __name__ == '__main__':
    sample_app = MyApp()
    sample_app.run()
