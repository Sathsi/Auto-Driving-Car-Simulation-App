package com.gic.model;

public class CarInputDetails {
    private String name;
    private Position initialPosition;
    private String commands;

    public CarInputDetails(String name, Position initialPosition, String commands) {
        this.name = name;
        this.initialPosition = initialPosition;
        this.commands = commands;
    }

    public CarInputDetails() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Position getInitialPosition() {
        return initialPosition;
    }

    public void setInitialPosition(Position initialPosition) {
        this.initialPosition = initialPosition;
    }

    public String getCommands() {
        return commands;
    }

    public void setCommands(String commands) {
        this.commands = commands;
    }
}
