package com.gic;

import com.gic.model.CarInputDetails;
import com.gic.model.Position;
import com.gic.processor.AutonomousCarProcessor;
import com.gic.processor.impl.AutonomousCarProcessorImpl;
import com.gic.utils.RequestValidator;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AutoDrivingCarApplication {

    private static final Logger LOGGER = Logger.getLogger(AutoDrivingCarApplication.class);

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
        if ("1".equals(method.trim())) {
            System.out.println("Enter initial position (x y direction):");
            String[] initialPosition = scanner.nextLine().split(" ");
            int x = Integer.parseInt(initialPosition[0]);
            int y = Integer.parseInt(initialPosition[1]);
            String startDirection = initialPosition[2];
            System.out.println("Enter the commands (FFRL):");
            String commands = scanner.nextLine();
            carInputDetailsList.add(new CarInputDetails("", new Position(x, y, startDirection), commands));

        } else if ("2".equals(method.trim())) {
            System.out.println("enter the details for each car in the following format. Press enter again once finish entering details");
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

        }

        try {
            RequestValidator requestValidator = new RequestValidator();
            AutonomousCarProcessor autonomousCarProcessor = new AutonomousCarProcessorImpl(requestValidator);
            MoveCar moveCar = new MoveCar(autonomousCarProcessor);
            moveCar.carMove(method, width, height, carInputDetailsList);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

    }

}
