#import ~/../../usr/lib/python3/dist-packages/kivy
import kivy
import socket
from kivy.app import App
from kivy.uix.label import Label


class MyApp(App):
    #Protip: don't write things in the __init__ method. It screws everything up.
    def build(self):
        HOST = '192.168.33.217'
        PORT = 13379
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.connect((HOST, PORT))
        print("connected!")
        b = bytearray()
        b.extend('kivy sssuuucckkss')
        print(b)
        s.send(b)
        return Label(text='Hello world')

if __name__ == '__main__':
    MyApp().run()
