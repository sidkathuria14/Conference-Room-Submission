package org.example;

import org.example.commands.Features;
import org.example.models.*;

import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        ManagementSystem managementSystem = new ManagementSystem();

        Building building = new Building("alpha");
        Floor floor = new Floor(1);
        User user = new User("Sid", 1);
        ConferenceRoom conferenceRoom = new ConferenceRoom(10, "a1", building, floor);

        // Initializing
        managementSystem.addBuilding(building);
        managementSystem.addFloor(floor, building);
        managementSystem.addConferenceRoom(building, floor, conferenceRoom);
        Features features = new Features(managementSystem);

        // Registering a user
        features.registerUser(user);
        List<User> users = managementSystem.getUsers();

        // Booking a conf room
        features.bookConferenceRoom(new Slot(4, 8), user, conferenceRoom);
        List<Booking> bookings = features.getBookings(user);

        // Testing available conf rooms logic
        int capacity = features.getCapacity(conferenceRoom);
        List<ConferenceRoom> conferenceRoomList = features.getSuitableRooms(new Slot(5, 16));
        List<ConferenceRoom> conferenceRoomList1 = features.getSuitableRooms(new Slot(1, 3));
        List<ConferenceRoom> conferenceRoomList2 = features.getSuitableRooms(new Slot(1, 4));
        List<ConferenceRoom> conferenceRoomList3 = features.getSuitableRooms(new Slot(9, 12));


        conferenceRoomList.size();

        // Cancelling a booking
        features.cancelBooking(user, bookings.get(0));
        managementSystem.getBookings().size();


    }
}