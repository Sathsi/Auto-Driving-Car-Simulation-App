package com.gic.util;

import com.gic.exception.CarInputDetailValidationException;
import com.gic.model.Position;
import com.gic.model.ValidationConst;
import com.gic.utils.InputValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InputValidatorTest {

    private InputValidator inputValidator = new InputValidator();

    @Test
    public void whenInvalidStartCoordinatesThenThrowException() throws Exception {
        try {
            Position position = new Position(1,-2,"N");
            inputValidator.validateCarAutoDriveInputDetails(10,10,position, "FRFF");
        } catch (CarInputDetailValidationException e) {
            // Assert that the exception message is correct
            assertEquals(ValidationConst.INVALID_COORDINATES.message(), e.getMessage());
        }
    }

    @Test
    public void whenInvalidStartDirectionThenThrowException() throws Exception {
        try {
            Position position = new Position(1,2,"N");
            inputValidator.validateCarAutoDriveInputDetails(10,10,position, "FRFF");
        } catch (CarInputDetailValidationException e) {
            // Assert that the exception message is correct
            assertEquals(ValidationConst.INVALID_DIRECTION.message(), e.getMessage());
        }
    }

    @Test
    public void whenInvalidCommandThenThrowException() throws Exception {
        Position position  = mock(Position.class);
        when(position.getDirection()).thenReturn("N");
        when(position.getX()).thenReturn(1);
        when(position.getY()).thenReturn(2);
        try {
            inputValidator.validateCarAutoDriveInputDetails(10,10,position,"FFRTF");
        } catch (CarInputDetailValidationException e) {
            // Assert that the exception message is correct
            assertEquals(ValidationConst.INVALID_COMMAND.message(), e.getMessage());
        }
    }
}
