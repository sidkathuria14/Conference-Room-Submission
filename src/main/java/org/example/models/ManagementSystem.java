package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class ManagementSystem {

    private List<User> users;
    private List<Booking> bookings;
    private List<Building> buildings;

    public ManagementSystem() {
        this.users = new ArrayList<>();
        this.bookings = new ArrayList<>();
        this.buildings = new ArrayList<>();
    }

    public void addBuilding(Building building){
        buildings.add(building);
    }

    public void addFloor(Floor floor, Building building){
        buildings.stream().filter(building1 -> building.getName().equals(building1.getName()))
                .findFirst().get().addFloor(floor);
    }

    public void addUser(User user){
        this.users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public List<Building> getBuildings() {
        return buildings;
    }
    public void addConferenceRoom(Building building, Floor floor, ConferenceRoom conferenceRoom){
        Building filteredBuilding = this.buildings.stream().filter(iterableBuilding -> iterableBuilding.getId().equals(building.getId())).findFirst().get();
        Floor filteredFloor = filteredBuilding.getFloors().stream().filter(iterableFloor -> iterableFloor.getFloor() == floor.getFloor()).findFirst().get();
        filteredFloor.addConferenceRoom(conferenceRoom);
    }

    public void addBooking(Slot slot, User user, ConferenceRoom conferenceRoom){
        this.bookings.add(new Booking(slot, conferenceRoom, user));
    }
}
