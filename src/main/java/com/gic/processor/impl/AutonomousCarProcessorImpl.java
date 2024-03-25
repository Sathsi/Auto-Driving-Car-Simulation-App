package com.gic.processor.impl;

import com.gic.model.CarCollisionDetails;
import com.gic.model.CarInputDetails;
import com.gic.model.Position;
import com.gic.model.Direction;
import com.gic.processor.AutonomousCarProcessor;
import com.gic.utils.InputValidator;

import java.util.List;

public class AutonomousCarProcessorImpl implements AutonomousCarProcessor {

    private final InputValidator inputValidator;

    public AutonomousCarProcessorImpl(InputValidator inputValidator) {
        this.inputValidator = inputValidator;
    }

    //Get car ending position with given input details
    @Override
    public String getCarEndingPositionAndDirection(int width, int height, CarInputDetails carInputDetails) throws Exception {

        inputValidator.validateCarAutoDriveInputDetails(width, height,carInputDetails.getInitialPosition()
                ,carInputDetails.getCommands());

        Position finalCarPosition = calculateCarEndingPosition(width, height, carInputDetails.getInitialPosition().getX()
                , carInputDetails.getInitialPosition().getY(), carInputDetails.getInitialPosition().getDirection(),
                carInputDetails.getCommands());

        return getCarMoveResult(finalCarPosition);
    }

    //Check cars collied when multiple cars deploy in same field at the same time
    @Override
    public String checkCarCollisionHappen(int width, int height, List<CarInputDetails> carInputDetailsList) throws Exception {

        inputValidator.validateCarAutoDriveInputDetails(width, height, carInputDetailsList);

        CarCollisionDetails carCollisionDetails = null;

        //Find the maximum command length among all cars. Max length is consider as max driving steps
        int maxDrivingSteps = carInputDetailsList.stream()
                .mapToInt(car -> car.getCommands().length())
                .max()
                .orElse(0);

        CarInputDetails carOne = carInputDetailsList.get(0);
        CarInputDetails carTwo = carInputDetailsList.get(1);

        Position carOnePosition = carOne.getInitialPosition();
        Position carTwoPosition = carTwo.getInitialPosition();

        // Check for collision with other cars
        for(int i=0; i < maxDrivingSteps; i++){
            carOnePosition = calculateCarEndingPosition(width, height
                    ,carOnePosition.getX()
                    ,carOnePosition.getY()
                    ,carOnePosition.getDirection()
                    , String.valueOf(carOne.getCommands().toCharArray()[i]));

            carTwoPosition = calculateCarEndingPosition(width, height
                    ,carTwoPosition.getX()
                    ,carTwoPosition.getY()
                    ,carTwoPosition.getDirection()
                    , String.valueOf(carTwo.getCommands().toCharArray()[i]));

            //check the two position is equal to check the cars collide each other
            if(carOnePosition.getX() == carTwoPosition.getX() &&
                    carOnePosition.getY() == carTwoPosition.getY()){
                carCollisionDetails = new CarCollisionDetails(carOne.getName() + " " + carTwo.getName()
                        ,carOnePosition.getX() + " " + carOnePosition.getY()
                        ,i+1);

                //log got result
                break;
            }
        }
        return getCollisionResponse(carCollisionDetails);

    }

    //Calculate the car position after one or multiple movements
    private Position calculateCarEndingPosition(int width, int height, int startXCoordinate, int startYCoordinate,
                                                String direction, String commands){
        // Define movements based on direction
        int[][] movements = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int x = startXCoordinate;
        int y = startYCoordinate;

        String[] commandArray = commands.split("");

        for (String command : commandArray) {
            if (command.equals("F")) {
                int index = indexOf(Direction.toStringArray(), direction); // Read the index of current position based on the direction
                int dx = movements[index][0]; // Based on the current index, get the dx value
                int dy = movements[index][1]; // Based on the current index, get the dy value
                int newX = x + dx;
                int newY = y + dy;

                //Check the boundary conditions
                if (newX >= 0 && newX <= width && newY >= 0 && newY <= height) {
                    x = newX;
                    y = newY;
                }
                /* Check the new direction of the car if command is L
                eg: current direction is N, then with L new direction is W */
            } else if (command.equals("L")) {
                direction = rotateLeft(direction);
            } else if (command.equals("R")) {
                direction = rotateRight(direction);
            }
        }

        return new Position(x,y,direction);

    }

    private int indexOf(String[] array, String value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    //Check the direction when car rotate left
    private String rotateLeft(String direction) {
        switch (direction) {
            case "N":
                return Direction.W.name();
            case "E":
                return Direction.N.name();
            case "S":
                return Direction.E.name();
            case "W":
                return Direction.S.name();
            default:
                return direction;
        }
    }

    //Check the direction when car rotate right
    private String rotateRight(String direction) {
        switch (direction) {
            case "N":
                return Direction.E.name();
            case "E":
                return Direction.S.name();
            case "S":
                return Direction.W.name();
            case "W":
                return Direction.N.name();
            default:
                return direction;
        }
    }

    //Generate final result of the car movement
    private String getCarMoveResult(Position position){
        String result = "";
        if(position != null){
            StringBuilder stringBuilder = new StringBuilder();
            result = stringBuilder.append(position.getX())
                    .append(" ")
                    .append(position.getY())
                    .append(" ")
                    .append(position.getDirection()).toString();

        }
        return result;
    }

    //Generate the final result from the operation
    private String getCollisionResponse(CarCollisionDetails carCollisionDetails){
        String result = "No Collision";
        if(carCollisionDetails != null){
            StringBuilder stringBuilder = new StringBuilder();
            result = stringBuilder.append(carCollisionDetails.getCarNames())
                    .append("\n")
                    .append(carCollisionDetails.getCollisionPosition())
                    .append("\n")
                    .append(carCollisionDetails.getStep()).toString();

        }
        return result;

    }
}
