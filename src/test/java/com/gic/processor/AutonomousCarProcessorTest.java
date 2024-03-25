package com.gic.processor;

import com.gic.model.CarInputDetails;
import com.gic.model.Position;

import com.gic.processor.impl.AutonomousCarProcessorImpl;
import com.gic.utils.InputValidator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AutonomousCarProcessorTest {

    private static AutonomousCarProcessor autonomousCarProcessor;

    private static final InputValidator requestValidator = mock(InputValidator.class);
    private static final CarInputDetails car1 = mock(CarInputDetails.class);
    private static final CarInputDetails car2 = mock(CarInputDetails.class);
    private static final CarInputDetails car3 = mock(CarInputDetails.class);

    private static final Position position1 = mock(Position.class);
    private static final Position position2 = mock(Position.class);
    private static final Position position3 = mock(Position.class);

    @BeforeAll
    public static void beforeSetup(){

        autonomousCarProcessor = new AutonomousCarProcessorImpl(requestValidator);

        when(position1.getDirection()).thenReturn("N");
        when(position1.getX()).thenReturn(1);
        when(position1.getY()).thenReturn(2);

        when(car1.getCommands()).thenReturn("FFRFFFFRRL");
        when(car1.getInitialPosition()).thenReturn(position1);
        when(car1.getName()).thenReturn("A");

        when(position2.getDirection()).thenReturn("W");
        when(position2.getX()).thenReturn(7);
        when(position2.getY()).thenReturn(8);

        when(car2.getCommands()).thenReturn("FFLFFFFFFF");
        when(car2.getInitialPosition()).thenReturn(position2);
        when(car2.getName()).thenReturn("B");

        when(position3.getDirection()).thenReturn("E");
        when(position3.getX()).thenReturn(6);
        when(position3.getY()).thenReturn(3);

        when(car3.getCommands()).thenReturn("FFRFFFRRLF");
        when(car3.getInitialPosition()).thenReturn(position3);
        when(car3.getName()).thenReturn("C");

    }

    // All test cases use valid inputs

    @Test
    public void whenDeployOneCar_ThenReturnEndPosition() throws Exception {
        String carEndingPosition = autonomousCarProcessor.getCarEndingPositionAndDirection(10,10, car1);
        String[] finalResult = carEndingPosition.split(" ");
        assertNotNull(carEndingPosition);
        assertEquals("5",finalResult[0]);
        assertEquals("4",finalResult[1]);
        assertEquals("S",finalResult[2]);

    }

    @Test
    public void whenDeployMultipleCar_ThenCheckCollision_Collide() throws Exception {
        List<CarInputDetails> collisionCarList = new ArrayList<>();
        collisionCarList.add(car1);
        collisionCarList.add(car2);
        String expectedResult = "A B\n5 4\n7";

        String collisionResult  = autonomousCarProcessor.checkCarCollisionHappen(10,10,collisionCarList);
        assertNotNull(collisionResult);
        assertEquals(expectedResult,collisionResult);
    }

    @Test
    public void whenDeployMultipleCar_ThenCheckCollision_NoCollision() throws Exception {
        List<CarInputDetails> noCollisionCarList = new ArrayList<>();
        noCollisionCarList.add(car1);
        noCollisionCarList.add(car3);
        String expectedResult = "No Collision";

        String collisionResult  = autonomousCarProcessor.checkCarCollisionHappen(10,10,noCollisionCarList);
        assertNotNull(collisionResult);
        assertEquals(expectedResult,collisionResult);
    }

    @Test
    public void whenDeployOneCar_OutBoundaryCommand_ThenReturnEndPosition() throws Exception {
        CarInputDetails car = mock(CarInputDetails.class);
        when(car.getCommands()).thenReturn("FFRFFFFFRL");
        when(car.getInitialPosition()).thenReturn(position1);
        when(car.getName()).thenReturn("A");

        //Here 8th command F is ignored since it move car outside of the boundary
        String carEndingPosition = autonomousCarProcessor.getCarEndingPositionAndDirection(5,5, car);
        String[] finalResult = carEndingPosition.split(" ");
        assertNotNull(carEndingPosition);
        assertEquals("5",finalResult[0]);
        assertEquals("4",finalResult[1]);
        assertEquals("E",finalResult[2]);

    }

    @Test
    public void whenDeployMultipleCar_OutBoundaryCommand_ThenCheckCollision_Collide() throws Exception {
        CarInputDetails car = mock(CarInputDetails.class);
        Position position = mock(Position.class);

        when(position.getDirection()).thenReturn("E");
        when(position.getX()).thenReturn(3);
        when(position.getY()).thenReturn(1);

        when(car.getCommands()).thenReturn("FFFFLFFFLF");
        when(car.getInitialPosition()).thenReturn(position);
        when(car.getName()).thenReturn("B");

        //Here car 2 3rd F and 4th F commands are ignored since it move car outside of the boundary
        List<CarInputDetails> collisionCarList = new ArrayList<>();
        collisionCarList.add(car1);
        collisionCarList.add(car);
        String expectedResult = "A B\n5 4\n8";

        String collisionResult  = autonomousCarProcessor.checkCarCollisionHappen(5,5,collisionCarList);
        assertNotNull(collisionResult);
        assertEquals(expectedResult,collisionResult);
    }

    @Test
    public void whenDeployMultipleCar_OutBoundaryCommand_ThenCheckCollision_NoCollision() throws Exception {
        CarInputDetails car = mock(CarInputDetails.class);
        Position position = mock(Position.class);

        when(position.getDirection()).thenReturn("E");
        when(position.getX()).thenReturn(3);
        when(position.getY()).thenReturn(1);

        when(car.getCommands()).thenReturn("FFFFLFFLFR");
        when(car.getInitialPosition()).thenReturn(position2);
        when(car.getName()).thenReturn("B");

        //Here car 2 3rd F and 4th F commands are ignored since it move car outside of the boundary
        List<CarInputDetails> collisionCarList = new ArrayList<>();
        collisionCarList.add(car1);
        collisionCarList.add(car);
        String expectedResult = "No Collision";

        String collisionResult  = autonomousCarProcessor.checkCarCollisionHappen(5,5,collisionCarList);
        assertNotNull(collisionResult);
        assertEquals(expectedResult,collisionResult);
    }

}
