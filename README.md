# CAD Buddy
### Softdes Final Project, Spring 2018
This project explores the use of a smart phone as an advanced form of input for laptop computers. Rather than using a mouse with relatively few sensors, buttons, and methods for feedback, we decided to try to leverage the many sensors and features of smartphones in order to create a cheap and effective tool for interacting with computers.

Our current project uses gyroscope data streaming from a smartphone in order to intuitively interact the a CAD model in solidworks. Changes to the pitch and roll of the phone directly correlate to changes of the CAD model's orientation.

## Authors
### Main Developers
* [Hyegi Bang](https://github.com/hyegibang)
* [Jonathan Yee](https://www.github.com/jzerez)
### Other software used
 [pynput v 1.3.10](https://pypi.python.org/pypi/pynput): This library is being used to parse simulated keystrokes as to interact with solidworks. The current version is compatible with Python version 3.6
* [win32gui v 221.6](https://pypi.python.org/pypi/win32gui/221.6): This library is being used to monitor the current screens, as to make the app dynamic and prevent interference with programs other than solidworks. The current version is compatible with Python version 3.6

## Getting started (Quick Start)
This section will go over how to install the app if you simply just want to use it. We tried to make installing this app as quick and easy as possible. First, ensure that both your android device and computer are on the same wifi network. Secondly, install our solidworks shortcut package:
`...\MouseRemote\solidworks\CAD_BUDDY.sldreg`
This can be easily done by opening "Copy Settings Wizard", and loading the appropriate sldreg file. Then, run the following file:
`...\MouseRemote\dist\main.exe`
Once running, choose a port number, an integer between 5000 and 65535. Next, run `ipconfig` within the windows command prompt and find your computers IPv4 address. Then, use android studio to install the Android Studio project, `SwitchScreen` to your android device. Once the app is running, input the correct IPv4 Address and Port number, and you're ready to open solidworks and start CADing!

## Getting started (Advanced)
This sectin will go over how to install the app if you intention is to develop it. In order to use the app, you must be in windows, as solidworks doesn't operate in ubuntu. To download the required python packages run:
`sudo pip install -r requirements.txt`
It should install the necessary packages, as well as their respective dependencies.

From here, the process should be about the same. However, once the dependencies have been installed, the following python script
`...\MouseRemote\solidworks> python main.py`
can be used to run the computer side of the project, rather than the original executable.

## How to use:
Within solidwoks, when you move your phone, your solidworks part will also rotate. You will also be able to press buttons on the phone that correspond to shortcuts in solidworks. This is not really meant to replace your mouse, but rather is just something that is cool that can complement your mouse and hopefully make you CAD faster.

## Next Steps:
There is a lot that we could do to improve our project, notably with making it easier to install and distribute. As it is now, the user has to do a lot of work to get the app to work.

In the future, we wish to implement the ability to cast customized buttons within the app, such that a sldreg file that overwrites native short cuts doesn't need to be used.

In addition, we believe that it would be much better if we could tie the project to a solidworks API in order to more smoothly change the orientation of the part, and more robustly select feature tools without having to emulate keyboard shortcuts.

## Notable Files: 
* [`...\MouseRemote\Sockets\client.py`](https://github.com/hyegibang/MouseRemote/blob/master/Sockets/client.py)
* [`...\MouseRemote\Sockets\server.py`](https://github.com/hyegibang/MouseRemote/blob/master/Sockets/server.py)

* [`...\MouseRemote\solidworks\main.py`](https://github.com/hyegibang/MouseRemote/blob/master/solidworks/main.py)
* [`...\MouseRemote\solidworks\window.py`](https://github.com/hyegibang/MouseRemote/blob/master/solidworks/window.py)
* [`...\MouseRemote\solidworks\shortcuts.py`](https://github.com/hyegibang/MouseRemote/blob/master/solidworks/shortcuts.py)

* [`...\MouseRemote\SwitchScreen\...\app\src\main\java\hbang\switchscreen\switchscreen\MainActivity.java`](https://github.com/hyegibang/MouseRemote/blob/master/SwitchScreen/app/src/main/java/hbang/switchscreen/switchscreen/MainActivity.java)
* [`...\MouseRemote\SwitchScreen\...\app\src\main\java\hbang\switchscreen\switchscreen\MainActivity.java`](https://github.com/hyegibang/MouseRemote/blob/master/SwitchScreen/app/src/main/java/hbang/switchscreen/switchscreen/ipconfig.java)
