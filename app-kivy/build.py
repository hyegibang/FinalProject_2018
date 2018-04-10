
import kivy
kivy.require('1.9.0')

from kivy.app import App
from kivy.lang import Builder
from kivy.uix.screenmanager import ScreenManager, Screen

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
            text: "Go to Screen 2"
            on_press:
                # You can define the duration of the change
                # and the direction of the slide
                root.manager.transition.direction = 'left'
                root.manager.transition.duration = 0.01
                root.manager.current = 'screen_two'
                value = 1
                print(value)
        Button:
            text: "b3"
            on_press:
                value = 2
                print(value)
        Button:
            text: "b4"
            on_press:
                value = 3
                print(value)

        Button:
            text: "b5"
            on_press:
                value = 4
                print(value)

        Button:
            text: "b6"
            on_press:
                value = 5
                print(value)

        Button:
            text: "b7"
            on_press:
                value= 6
                print(value)



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
    pass


class ScreenTwo(Screen):
    pass


# The ScreenManager controls moving between screens
screen_manager = ScreenManager()

# Add the screens to the manager and then supply a name
# that is used to switch screens
screen_manager.add_widget(ScreenOne(name="screen_one"))
screen_manager.add_widget(ScreenTwo(name="screen_two"))

class KivyTut2App(App):

    def build(self):
        return screen_manager

sample_app = KivyTut2App()
sample_app.run()
