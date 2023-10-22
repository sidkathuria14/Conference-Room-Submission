package org.example.models;

public class ConferenceRoom {

    private int capacity;
    private String name;

    private Building building;
    private Floor floor;


    public ConferenceRoom(int capacity, String name, Building building, Floor floor) {
        this.capacity = capacity;
        this.name = name;
        this.building = building;
        this.floor = floor;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }
}
