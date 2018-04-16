# CAD Buddy
### Softdes Final Project, Spring 2018
This project explores the use of a smart phone as an advanced form of input for laptop computers. Rather than using a mouse with relatively few sensors, buttons, and methods for feedback, we decided to try to leverage the many sensors and features of smartphones in order to create a cheap and effective tool for interacting with computers.

Our current project uses gyroscope data streaming from a smartphone in order to intuitively interact the a CAD model in solidworks. Changes to the pitch and roll of the phone directly correlate to changes of the CAD model's orientation.

## Authors
# Main Developers
* [Hyegi Bang](https://github.com/hyegibang)
* [Jonathan Yee](https://www.github.com/jzerez)
# Other software used
* [Sensorstream IMU+GPS](https://play.google.com/store/apps/details?id=de.lorenz_fenster.sensorstreamgps&hl=en): This android app was used to measure internal sensors in the phone, and stream said data over wifi sockets to the computer for processing.
* [kivy](https://kivy.org/#home): This library is being used to develop our own standalone android apps that are written in python.
* [buildozer](https://github.com/kivy/buildozer): This library is being used to build kivy app into an android app, and create an apk that can be pushed to a real android device.
* [pynput](https://pypi.python.org/pypi/pynput): This library is being used to parse simulated keystrokes as to interact with solidworks.
* [win32gui](https://pypi.python.org/pypi/win32gui/221.6): This library is being used to monitor the current screens, as to make the app dynamic and prevent interference with programs other than solidworks.

## Getting started
