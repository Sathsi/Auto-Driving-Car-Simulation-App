package com.gic.processor;

import com.gic.model.CarInputDetails;

import java.util.List;

public interface AutonomousCarProcessor {

    String getCarEndingPositionAndDirection(int width, int height, CarInputDetails carInputDetails) throws Exception;

    String checkCarCollisionHappen(int width, int height, List<CarInputDetails> carInputDetailsList) throws Exception;
}
