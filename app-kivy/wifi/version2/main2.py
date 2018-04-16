from kivy.app import App
from kivy.uix.label import Label
from server2 import *
from threading import Thread


class MyLabel(Label):

    def __init__(self, **kwargs):
        super(MyLabel,self).__init__(**kwargs)

        self.sock = MySocket()
        Thread(target=self.send_data).start()

    def send_data(self):
        while True:
            self.text = self.sock.send_data()
            #self.text = str(self.sock.send_data(), 'utf-8')
            #print(self.text)


class BoxApp(App):

     def build(self):
        return MyLabel()


if __name__ == '__main__':
     BoxApp().run()
