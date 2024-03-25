package com.gic.model;

public enum ValidationConst {

    INVALID_COORDINATES("Invalid coordinates"),
    INVALID_FIELD_SIZE("Invalid Field size"),
    INVALID_DIRECTION("Invalid direction"),
    INVALID_COMMAND("Invalid Command"),
    NO_MULTIPLE_CARS("Enter multiple car details"),
    NULL_DIRECTION("Start direction can not be null or empty"),
    NULL_COMMAND("Command can not be null or empty");

    private final String msg;

    ValidationConst(String msg) {
        this.msg = msg;
    }

    public String message() {
        return msg;
    }
}
