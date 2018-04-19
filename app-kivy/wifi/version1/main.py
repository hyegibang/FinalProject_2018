from kivy.app import App
from kivy.uix.label import Label
from client import *
from threading import Thread


class MyLabel(Label):

    def __init__(self, **kwargs):
        super(MyLabel,self).__init__(**kwargs)

        self.sock = MySocket()
        Thread(target=self.get_data).start()

    def get_data(self):
        while True:
            self.text = str(self.sock.get_data(), 'utf-8')
            print(self.text)


class BoxApp(App):

     def build(self):
        return MyLabel()


if __name__ == '__main__':
     BoxApp().run()
