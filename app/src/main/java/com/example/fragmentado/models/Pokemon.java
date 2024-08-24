package com.example.fragmentado.models;

public class Pokemon {
    private final String name;

    public Pokemon(String name) {
        this.name = name;
    }

    public String getName() {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }
}