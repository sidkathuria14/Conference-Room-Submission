package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class Floor {

    private List<ConferenceRoom> conferenceRoomList;
    private final int floor;

    public Floor(int floor) {
        this.conferenceRoomList = new ArrayList<>();
        this.floor = floor;
    }

    public List<ConferenceRoom> getConferenceRoomList() {
        return conferenceRoomList;
    }

    public void addConferenceRoom(ConferenceRoom conferenceRoom){
        this.conferenceRoomList.add(conferenceRoom);
    }

    public int getFloor() {
        return floor;
    }
}
