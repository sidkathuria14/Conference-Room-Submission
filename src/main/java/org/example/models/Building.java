package org.example.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Building {

    private List<Floor> floors;
    private String name;
    private UUID id;

    public Building(String name) {
        this.floors = new ArrayList<>();
        this.name = name;
        this.id = UUID.randomUUID();
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addFloor(Floor floor){
        this.floors.add(floor);
    }

    public UUID getId() {
        return id;
    }
}
