# Java Based Traffic Simulator
## Program Working Document

### Specification
The client wwant to have a city traffic simulator with different types of vehicles such as cars, motorcycles and buses which interacted with roads, intersections and traffic light. The program will take input from the user and also display the simulation and objects' status to the user using a simple GUI. This version of the program can simulate multiple car in moving on a two lane road, interact with the traffic light and move on the another road until the vehicles reach the end of the road, for the screen of the GUI.

### Decomposition
The problem can be broken into operate objects that interact with each other to achieve the desired behaviour. 
These objects include:

#### Model.Vehicle
The vehicla classs will be abstract, and have Model.Car, Model.Bubs and model.Motorbbike as subclasses. Vehicle class has the following attribute:
- *id* – an unique identifier that differenciate each vehicle.
- *Length* – the physical length of the vehicle.
- *Breadth* – the physical width of the vehicle, half the vehicle's length.
- *Speed* – how far can the vehicle travel for each simulation turn
- *Position* – where the vehicle is on the current road
- *Current Model.Road* – the road the vehiccle is currently moving on
- *Colour* - random color for each vehicle to differenciate between them.

A vehicle will be able to move using the move() method. A vehicle will mmove along a road, its speed depends on the speed limit of the that road. When the car is in the same position 
as a traffic light it will check its state before moving. IF the light is red the car will not move, if the light is green the car will continue to move to the next road. When the vehicles position is equal to the end of a road and there is no connected road it will stop and be removed ending the simulation. The draw() method can determine how a vehicle is represented graphically.

