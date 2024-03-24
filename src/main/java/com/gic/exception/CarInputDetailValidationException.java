package com.gic.exception;

import com.gic.model.ValidationConst;

public class CarInputDetailValidationException extends Exception{

    private ValidationConst validationValue;

    public CarInputDetailValidationException(ValidationConst validationValue, final String message) {
        super(message);
        this.validationValue = validationValue;
    }
}
