package org.example.models;

import java.util.UUID;

public class Booking {

    private Slot slot;
    private ConferenceRoom conferenceRoom;
    private User user;
    private UUID id;

    public Booking(Slot slot, ConferenceRoom conferenceRoom, User user) {
        this.slot = slot;
        this.conferenceRoom = conferenceRoom;
        this.user = user;
        this.id = UUID.randomUUID();
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public ConferenceRoom getConferenceRoom() {
        return conferenceRoom;
    }

    public void setConferenceRoom(ConferenceRoom conferenceRoom) {
        this.conferenceRoom = conferenceRoom;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UUID getId() {
        return id;
    }
}
