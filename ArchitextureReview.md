# Architectural Review:
### Hyegi Bang and Jonathan Zerez

## Link to Electronic Feedback form:
https://docs.google.com/forms/d/e/1FAIpQLSeYFfLpxBBQjB3R0cclBvXjtYRtVLgFIEx_oG4abMcU9955Ow/viewform?usp=sf_link

## Goals for this Review:
* Gauge general thoughts about the concept of the project idea, as well as possible ways to grow and change the project as to increase its complexity/usefulness.
* Receive feedback on the proposed software architecture design explained below

## Agenda for Review Session
* Exploration and explanation of the project
* Description of the high level architecture Design
* Possible future steps and stretch Goals
* Concurrent electronic feedback from the audience

## Background and context
Our project is to use a smartphone as an input device for a computer, specifically for the applications of CAD, and possibly visual electronic art. The goal is to have dynamic shortcuts for the CAD/art software on the phone that change based on context (ex: sketch shortcuts, assembly shortcuts, feature shortcuts, etc).  

The architecture of the system is a bit complex. Below is a list of classes that need to be implemented:
* Multiple classes or "activities" within the phone app that represent shortcuts for different contexts.
* A class that transmits information from relevant activities on the phone to the computer, either using wifi or bluetooth
* A class that takes the receives the data on the computer and properly executes the corresponding shortcuts in the desired programs.
* A class that monitors the current state of the third party program to provide feedback to the phone to update the current visible activity to show the most relevant shortcuts.

## Key Questions
* Would this project actually be useful?
* Is our project a bit underscoped?
* Would it be useful to try to extract accelerometer data from the phone to deduce position in 2D space like a mouse? Would that add functionality?

## Next Steps for Further Development
* 2D positional tracking to use the phone as a mouse to control the cursor
* 3D positional tracking to use the phone to interact with CAD models. 
