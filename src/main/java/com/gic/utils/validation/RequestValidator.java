package com.gic.utils.validation;

import com.gic.exception.CarInputDetailValidationException;
import com.gic.model.*;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

public class RequestValidator {

    //Validate all input values
    public void validateCarAutoDriveInputDetails(int width, int height, Position position, String commands) throws Exception{
        validateCoordinate(position.getX(), position.getY(), width, height);
        validateStartDirection(String.valueOf(position.getDirection()));
        validateCommands(commands);

    }

    //Validate all input values
    public void validateCarAutoDriveInputDetails (int width, int height, List<CarInputDetails> carInputDetailsList) throws Exception {
        if(carInputDetailsList.size() < 2){
            throw new CarInputDetailValidationException(ValidationConst.NO_MULTIPLE_CARS,
                    ValidationConst.NO_MULTIPLE_CARS.message());
        }
        for (CarInputDetails carInput : carInputDetailsList) {
            validateCarAutoDriveInputDetails(width, height, carInput.getInitialPosition(), carInput.getCommands());
        }
    }

    private void validateCoordinate (int x, int y, int width, int height) throws Exception {
        boolean isValid = true;
        if(width < 0 || height < 0){
            isValid = false;
        }
        if(x < 0 || y < 0){
            isValid = false;
        }
        if (x > width || y > height) {
            isValid = false;
        }

        if(!isValid){
            throw new CarInputDetailValidationException(ValidationConst.INVALID_COORDINATES,
                    ValidationConst.INVALID_COORDINATES.message());
        }
    }

    private void validateStartDirection(String startDirection) throws Exception {
        if(isBlank(startDirection)){
            throw new CarInputDetailValidationException(ValidationConst.NULL_DIRECTION,
                    ValidationConst.NULL_DIRECTION.message());
        }
        if(!Arrays.stream(Direction.values()).map(Enum::name).anyMatch(startDirection.toUpperCase()::equals)){
            throw new CarInputDetailValidationException(ValidationConst.INVALID_DIRECTION,
                    ValidationConst.INVALID_DIRECTION.message());
        }
    }

    private void validateCommands(String commands) throws Exception {
        if(isBlank(commands)){
            throw new CarInputDetailValidationException(ValidationConst.NULL_COMMAND,
                    ValidationConst.NULL_COMMAND.message());
        }
        for (char command : commands.toCharArray()) {
            if (!Arrays.stream(Command.values())
                    .map(Enum::name)
                    .anyMatch(String.valueOf(command).toUpperCase()::equals)) {
                throw new CarInputDetailValidationException(ValidationConst.INVALID_COMMAND,
                        ValidationConst.INVALID_COMMAND.message());
            }
        }

    }

    private boolean isBlank(String text){
        return StringUtils.isBlank(text);
    }

}
