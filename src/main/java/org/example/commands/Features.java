package org.example.commands;

import org.example.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Features {

    private final ManagementSystem managementSystem;

    public Features(ManagementSystem managementSystem){
        this.managementSystem = managementSystem;
    }

    public void registerUser(User user){
        managementSystem.addUser(user);
    }

    public void bookConferenceRoom(Slot slot, User user, ConferenceRoom conferenceRoom){
        managementSystem.addBooking(slot, user, conferenceRoom);
    }

    // get All Conference Rooms in a building with optional capacity and availability
    public List<ConferenceRoom> getConferenceRooms(Building building, Optional<Integer> capacity){
        List<ConferenceRoom> conferenceRoomList = new ArrayList<>();

        List<ConferenceRoom> finalConferenceRoomList = conferenceRoomList;
        building.getFloors().forEach(floor -> finalConferenceRoomList.addAll(floor.getConferenceRoomList()));

        if(capacity.isPresent()){
            conferenceRoomList = conferenceRoomList.stream().filter( conferenceRoom -> conferenceRoom.getCapacity() > capacity.get()).toList();
        }
        return conferenceRoomList;
    }

    public List<ConferenceRoom> getConferenceRooms(Building building, Floor floor, Optional<Integer> capacity){
        List<ConferenceRoom> conferenceRoomList = new ArrayList<>();

        List<ConferenceRoom> finalConferenceRoomList = conferenceRoomList;
        building.getFloors().stream().filter(buildingIterable -> buildingIterable.getFloor() == floor.getFloor()).forEach(floorIterable -> finalConferenceRoomList.addAll(floorIterable.getConferenceRoomList()));

        if(capacity.isPresent()){
            conferenceRoomList = conferenceRoomList.stream().filter( conferenceRoom -> conferenceRoom.getCapacity() > capacity.get()).toList();
        }
        return conferenceRoomList;
    }

    public List<ConferenceRoom> getSuitableRooms(Slot slot, Building building, Floor floor){

        List<ConferenceRoom> suitableConferenceRooms = new ArrayList<>();

        List<ConferenceRoom> conferenceRoomList = managementSystem.getBuildings().stream().filter(building1 -> building1.getName().equals(building.getName())).findFirst().get().getFloors().stream().filter(floorIterable -> floorIterable.getFloor() == floor.getFloor()).findFirst().get().getConferenceRoomList();
        List<Booking> bookings = managementSystem.getBookings().stream().filter(booking -> conferenceRoomList.contains(booking.getConferenceRoom())).collect(Collectors.toList());
        List<ConferenceRoom> conferenceRoomsWithBooking = bookings.stream().map(booking -> booking.getConferenceRoom()).toList();
        conferenceRoomList.stream().filter(conferenceRoom -> !conferenceRoomsWithBooking.contains(conferenceRoom)).forEach(suitableConferenceRooms::add);

        bookings.stream().forEach(booking -> {
            if(slot.getStartTime() >= booking.getSlot().getEndTime() || slot.getEndTime() <= booking.getSlot().getStartTime()){
                suitableConferenceRooms.add(booking.getConferenceRoom());
            }
        });

        return suitableConferenceRooms;
    }

    public List<ConferenceRoom> getSuitableRooms(Slot slot){

        List<ConferenceRoom> suitableConferenceRooms = new ArrayList<>();

        managementSystem.getBuildings().stream().forEach(building -> {
            building.getFloors().forEach(floor -> {
                suitableConferenceRooms.addAll(getSuitableRooms(slot, building, floor));
            });
        });

        return suitableConferenceRooms;
    }

    public List<ConferenceRoom> getSuitableRooms(Slot slot, Building building){

        List<ConferenceRoom> suitableConferenceRooms = new ArrayList<>();

        Building filteredBuilding = managementSystem.getBuildings().stream().filter(buildingIterable -> building.getId().equals(buildingIterable.getId())).findFirst().get();
        filteredBuilding.getFloors().forEach(floor -> suitableConferenceRooms.addAll(getSuitableRooms(slot, filteredBuilding, floor)));
        return suitableConferenceRooms;
    }

    public void cancelBooking(User user, Booking booking){
        Booking filteredBooking = managementSystem.getBookings().stream().filter(booking1 -> booking1.getUser().equals(user)).filter(booking1 -> booking.getId().equals(booking1.getId())).findFirst().get();
        managementSystem.getBookings().remove(filteredBooking);
    }

    public List<Booking> getBookings(User user){
        return managementSystem.getBookings().stream().filter(booking -> booking.getUser().equals(user)).collect(Collectors.toList());
    }


    public int getCapacity(ConferenceRoom conferenceRoom){
        return conferenceRoom.getCapacity();
    }




}
