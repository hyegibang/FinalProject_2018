# CAD Buddy
### Softdes Final Project, Spring 2018
This project explores the use of a smart phone as an advanced form of input for laptop computers. Rather than using a mouse with relatively few sensors, buttons, and methods for feedback, we decided to try to leverage the many sensors and features of smartphones in order to create a cheap and effective tool for interacting with computers.

Our current project uses gyroscope data streaming from a smartphone in order to intuitively interact the a CAD model in solidworks. Changes to the pitch and roll of the phone directly correlate to changes of the CAD model's orientation.

## Authors
### Main Developers
* [Hyegi Bang](https://github.com/hyegibang)
* [Jonathan Yee](https://www.github.com/jzerez)
### Other software used
* [Sensorstream IMU+GPS](https://play.google.com/store/apps/details?id=de.lorenz_fenster.sensorstreamgps&hl=en): This android app was used to measure internal sensors in the phone, and stream said data over wifi sockets to the computer for processing.
* [kivy](https://kivy.org/#home): This library is being used to develop our own standalone android apps that are written in python.
* [buildozer](https://github.com/kivy/buildozer): This library is being used to build kivy app into an android app, and create an apk that can be pushed to a real android device.
* [pynput](https://pypi.python.org/pypi/pynput): This library is being used to parse simulated keystrokes as to interact with solidworks.
* [win32gui](https://pypi.python.org/pypi/win32gui/221.6): This library is being used to monitor the current screens, as to make the app dynamic and prevent interference with programs other than solidworks.

## Getting started
In order to use the app, you must be in windows, as solidworks doesn't operate in ubuntu. To download the required python packages run:
`sudo pip install -r requirements.txt`
It should install the necessary packages, as well as their respective dependencies.

In addition, you will need to install [SensorStream](https://play.google.com/store/apps/details?id=de.lorenz_fenster.sensorstreamgps&hl=en_GB) to an android phone.

## Usage
To use this program, you must first know your IP address. In the windows console, type:
`ipconfig`
which will display some information about your computer's wireless connections. Remember the sequence of numbers that represent your IPv4 Address..

Next, you must configure the SensorStream app. Upon opening, go to the "Toggle Sensors" section at the top. Once at the new page, check the box for "Orientation" and the box that says "Include User-Checked Sensor Data in Stream". Go back to the "Preferences" page by pressing the left button at the top. For the target IP address, ensure your phone is on the same wifi as your computer, and then type in the IPv4 address from earlier. For the port number, any valid port number can be chosen (ex: 5555, 8888, 23681, etc). Next check the radio button that says "UDP Stream". Finally, press the button that says "Start Stream".

Back on the laptop side of things, once you open a part in solidworks, you are ready to run the program. Execute the following:
`...\Mouseremote\Sockets> python new_server.py <IPv4 ADDRESS> <PORT NUMBER>`
When you move your phone, your solidworks part will also rotate. 
