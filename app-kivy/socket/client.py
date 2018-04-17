from kivy.app import App
from kivy.uix.boxlayout import BoxLayout
from kivy.properties import ObjectProperty
import socket
from kivy.lang import Builder

# You can create your kv code in the Python file



class BoxWidget(BoxLayout):
    s = socket.socket()
    host = '127.0.0.1'
    port = 7000
    display = ObjectProperty()


    def connect_to_server(self):
        # called by a Button press

        # Connects to the server
        self.s.connect((self.host, self.port))

        # Receives confirmation from Server
        data = self.s.recv(1024).decode()

        # Converts confirmation to string
        strdata = str(data)

        # Prints confirmation
        print(strdata)

    def send_message(self):
        # Is called by the function below
        # Encodes and sends the message variable
        self.s.send(self.message.encode())

        # Waits for a reply
        self.receive_message()
    def message_to_send(self):
       # Defines Message to send
       self.message = self.display.text
       # Calls function to send the message
       self.send_message()

   # Note
   # When I used message = input directly in send_message,
   # the app would crash. So I defined message input
   # in its own function which then calls the
   # send function

   # message_to_send is the function actually
   # called by a button press which then
   # starts the chain of events
   # Define Message, Send Message, get Reply

    def receive_message(self):
       # Decodes a reply
       reply = self.s.recv(1024).decode()

       # Converts reply to a str
       strreply = str(reply)

       # prints reply
       print(strreply)

class ServerApp(App):
     def build(self):
          box = BoxWidget()
          return box

if __name__ == '__main__':
    ServerApp().run()
