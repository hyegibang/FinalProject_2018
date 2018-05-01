Note: our current website can be found at http://hyegibang.github.io/MouseRemote

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

## Getting started
In order to use the app, you must be in windows, as solidworks doesn't operate in ubuntu. To download the required python packages run:
`sudo pip install -r requirements.txt`
It should install the necessary packages, as well as their respective dependencies.

In addition, you will need to install our mobile app, which only works on Android devices. Currently, the only way to do so is to clone the repository and to install the project APK manually using Android Studio. In the future, we will try to upload it to the play store for easier distribution.

## Usage
To use this program, you must first know your IP address. In the windows console, type:
`ipconfig`
which will display some information about your computer's wireless connections. Remember the sequence of numbers that represent your IPv4 Address.

Next, you must configure the mobile app. First, ensure that the mobile device is on the same wifi network as the computer. Then, open the following file:
`...\MouseRemote\SwitchScreen\app\src\main\java\com\example\hbang\swithcscreen\MainActivity.java`.
 Upon opening, change the `HOST` String in line 26 to the IPv4 address from your computer. Install the application using Android Studio.

The second to last step is to install our solidworks shortcut package:
`...\MouseRemote\solidworks\CAD_BUDDY.sldreg`
This can be easily done by opening "Copy Settings Wizard", and loading the appropriate sldreg file.

Finally, once you open a part in solidworks, you are ready to run the program. Execute the following:
`...\Mouseremote\Solidworks> python main.py`
Then start the app on your phone. When you move your phone, your solidworks part will also rotate. You will also be able to press buttons on the phone that correspond to shortcuts in solidworks.

## Next Steps:
There is a lot that we could do to improve our project, notably with making it easier to install and distribute. As it is now, the user has to do a lot of work to get the app to work.

In the future, we wish to implement a user interface to collect the computers IP within the mobile app. In addition, we wish to implement the ability to cast customized buttons within the app, such that a sldreg file that overwrites native short cuts doesn't need to be used.

In addition, we believe that it would be much better if we could tie the project to a solidworks API in order to more smoothly change the orientation of the part, and more robustly select feature tools without having to emulate keyboard shortcuts. 
