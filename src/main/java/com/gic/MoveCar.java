package com.gic;

import com.gic.model.CarInputDetails;
import com.gic.processor.AutonomousCarProcessor;

import java.util.List;

public class MoveCar {
    private final AutonomousCarProcessor autonomousCarProcessor;

    public MoveCar(AutonomousCarProcessor autonomousCarProcessor) {
        this.autonomousCarProcessor = autonomousCarProcessor;
    }

    //Start the car movements
    public String carMove(String method, int width, int height, List<CarInputDetails> carInputDetailsList) throws Exception {
        String finalResult = "";
        if ("1".equals(method.trim())) {
            finalResult = autonomousCarProcessor.getCarEndingPositionAndDirection(width, height, carInputDetailsList.get(0));

        } else if ("2".equals(method.trim())) {
            finalResult = autonomousCarProcessor.checkCarCollisionHappen(width, height, carInputDetailsList);
        } else
            System.out.println("Invalid method invoke. Please send correct method");

        System.out.println(finalResult);

        return finalResult;
    }

}
