package com.gic.model;

public class CarCollisionResponse {
    private String carNames;
    private String collisionPosition;
    private int step;

    public CarCollisionResponse(String carNames, String collisionPosition, int step) {
        this.carNames = carNames;
        this.collisionPosition = collisionPosition;
        this.step = step;
    }

    public String getCarNames() {
        return carNames;
    }

    public void setCarNames(String carNames) {
        this.carNames = carNames;
    }

    public String getCollisionPosition() {
        return collisionPosition;
    }

    public void setCollisionPosition(String collisionPosition) {
        this.collisionPosition = collisionPosition;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }
}
