package com.jkolacz.rentalapplication.domain.space;

public class SquareMeterException extends RuntimeException {
    SquareMeterException() {
        super("Square meter cannot be lower or equal zero.");
    }
}
