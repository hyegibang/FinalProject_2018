# Architectural Review 2:
### Hyegi Bang and Jonathan Zerez

## Goals for this Review:
* Explore ways to expand the scope of the project
* Find ways to more robustly handle edge cases (ex: names of solidworks files)
* Find out ways to distribute the project easily

## Agenda for Review Session
* Brief exploration and explanation of the project
* Description of the high level architecture Design
* Current Project Demo
* Next Steps
* Q&A

## Background and context
Our project is to use a smartphone as an input device for a computer, specifically for the applications of CAD. The goal is to have dynamic shortcuts for the CAD on the phone that change based on context (ex: sketch shortcuts, assembly shortcuts, feature shortcuts, etc), as well as have the phone able to control the viewing angle of the part based on the phones orientation.

The architecture of the system is a bit complex. Below is a list of classes that are used:
* A class, or "Activity" within the mobile app that holds specific buttons that represent shortcuts. Upon initialization, this class connects to a server socket started previously on a computer. This same class also send the orientation and button data to the laptop over the socket channel that was opened.
* A class that  receives the data on the computer and properly executes the corresponding shortcuts in the desired programs. We manipulate the view of the solidworks model, as well as input shortcuts by simulating keyboard presses on the PC.
* A class that monitors the current state of the third party program to provide feedback to the phone to update the current visible activity to show the most relevant shortcuts.

## Next Steps for Further Development
* More robust name and file detection
* Dynamic shortcuts on the phone
* sexy UI
* General cleanup for easy distro

## Key Questions
* We want this project to be useful as possible for as many people as possible. What could we add to make the project a bit more involved/cool/useful?
* How can we integrate different functionalities of the phone to provide more feedback (vibration, sound, etc)?
* What is an easy way to distribute our project when it is completed?
* Are there more elegant ways to handle edge cases for file names?
* Is there a way to elegantly interface with solidworks without an API?
