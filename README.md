# Auto-Driving-Car-Simulation
Auto Driving Car Simulation

## Content
- Assumptions
- Build the project
- Running the app
- End Points

## Assumptions
- To check collision, it only tests with 2 cars

### Build the project
```
   mvn clean install
```

### Running the app
java -jar automate-car-0.1.jar

## Flow
1. Once application is running, user can see the instructions to give the input from console

2. First enter the method you wanted to execute. 
    (ie. - 1-find the final car position.
         - 2-Check the collision happen if multiple cars deployed in same field at the same time.)
```
    Enter the method need to run (moveCar=1, checkCollision=2):
    1
```

2.2 then enter the field size
```
    Enter field size (width height):
    10 10
```

2.3 If you enter 1 in 1.1 step, then you can see below instructions to enter initial car position
```
    Enter initial position (x y direction):
    1 2 N
```

2.3.1 Then enter the commands you wanted to follow by car
```
    Enter the commands (FFRL):
    FFRFFFRRLF
```

2.3.2 now press enter to see the output

2.4 If you enter 2 in 1.1 step, then you can see below instructions to enter initial car position
```
    enter the details for each car in the following format. Press enter again once finish entering details
    ----- 
    name 
    x y direction 
    commands
    -----
```
give inputs like below. 
```
A
1 2 N
FFRFFFFRRL
B
7 8 W
FFLFFFFFFF
```

2.4.1 Once add all inputs press enter key twice to start the process