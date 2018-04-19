from kivy.app import App
from kivy.uix.boxlayout import BoxLayout
from kivy.lang import Builder
import socket

Builder.load_string('''
<RootWidget>:
        orientation: "vertical"
        TextInput:
                id: t1
                size_hint_y: 75
        Button:
                text: "send data"
                font_size: 75
                size_hint_y: 25
                on_press: root.send_data()
''')

class RootWidget(BoxLayout):
        def __init__(self):
                super(RootWidget, self).__init__()
                host="192.168.33.217"
                port=3000
                self.s=socket.socket()
                self.s.connect((host, port))
        def send_data(self):
                msg=str(self.ids.t1.text)
                encoded_msg=bytes(msg, "utf-8")
                self.s.send(encoded_msg)

class MyApp(App):
        def build(self):
                return RootWidget()

MyApp().run()
