import kivy
import socket
from kivy.app import App
from kivy.uix.label import Label


class MyApp(App):
    def __init__(self):
        HOST = '192.168.32.236'
        PORT = 42069
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.connect((HOST, PORT))
        print("connected!")
        s.send(bytes("hello world", 'utf-8'))

    def build(self):
        return Label(text='Hello world')


if __name__ == '__main__':
    MyApp().run()
