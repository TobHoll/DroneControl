package com.example.tobiashollarek.dronecontrol;

/**
 * Created by TobiasHollarek on 07.07.2017.
 */

enum Position {
    A("A"),
    B("B"),
    C("C");

    private final String name;

    Position(final String name) {
        this.name = name;
    }

    public String toString(){
        return name;
    }
}
