package com.gic;

import com.gic.model.CarInputDetails;
import com.gic.model.Position;
import com.gic.processor.AutonomousCarProcessor;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AutoDrivingCarApplication {

    private static final Logger LOGGER = Logger.getLogger(AutoDrivingCarApplication.class);
    private static AutonomousCarProcessor autonomousCarProcessor = new AutonomousCarProcessor();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Read which part need to test
        System.out.println("Enter the method need to run (moveCar=1, checkCollision=2):");
        String method = scanner.nextLine();

        // Read field dimensions
        System.out.println("Enter field size (width height):");
        String[] fieldDimensions = scanner.nextLine().split(" ");
        int width = Integer.parseInt(fieldDimensions[0]);
        int height = Integer.parseInt(fieldDimensions[1]);

        List<CarInputDetails> carInputDetailsList = new ArrayList<>();
        System.out.println("Enter details for each car as below (name x y direction commands):");
        System.out.println("----- \nname \nx y direction \ncommands\n-----");
        while (scanner.hasNextLine()) {
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) { // Exit loop if input is empty
                break;
            }
            String[] initialPosition = scanner.nextLine().split(" ");
            int x = Integer.parseInt(initialPosition[0]);
            int y = Integer.parseInt(initialPosition[1]);
            String startDirection = initialPosition[2];

            String commands = scanner.nextLine();
            carInputDetailsList.add(new CarInputDetails(name, new Position(x, y, startDirection), commands));
        }

        try {
            String finalResult = new AutoDrivingCarApplication().carMove(method, width, height, carInputDetailsList);
            System.out.println(finalResult);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

    }

    //Start the car movements
    public String carMove(String method, int width, int height, List<CarInputDetails> carInputDetailsList) throws Exception {
        String finalResult = "";
        if("1".equals(method.trim())){
            finalResult = autonomousCarProcessor.getCarEndingPositionAndDirection(width, height, carInputDetailsList.get(0));
            
        } else if("2".equals(method.trim())){
            finalResult = autonomousCarProcessor.checkCarCollisionHappen(width, height, carInputDetailsList);
        } else
            System.out.println("Invalid method invoke. Please send correct method");
        
        return finalResult;
    }
}
