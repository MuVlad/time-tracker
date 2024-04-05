package com.muslimov.vlad.timetracker.exception.model;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String massage) {
        super(massage);
    }
}