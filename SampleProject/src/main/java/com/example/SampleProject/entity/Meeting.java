package com.example.SampleProject.entity;




import jakarta.persistence.*;

import java.lang.reflect.Array;
import java.util.List;

@Entity
public class Meeting {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String title;
    private String description;
    private String meeting_date;
    private String start_time;

    private String end_time;

   @Column(name = "room_id")
    private String roomId;

    private String token_id;

    public String getMeetingStatus() {
        return meetingStatus;
    }

    public void setMeetingStatus(String meetingStatus) {
        this.meetingStatus = meetingStatus;
    }

    @Column(name = "meeting_status")
    private String meetingStatus;

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    @Transient
    private List<String> meeting_attendees;


    public String getMeeting_date() {
        return meeting_date;
    }

    public void setMeeting_date(String meeting_date) {
        this.meeting_date = meeting_date;
    }

    public String getDescription() {
        return description;
    }

    public String getStart_time() {
        return start_time;
    }


    public List<String> getMeeting_attendees() {
        return meeting_attendees;
    }

    public void setMeeting_attendees(List<String> meeting_attendees) {
        this.meeting_attendees = meeting_attendees;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken_id() {
        return token_id;
    }

    public void setToken_id(String token_id) {
        this.token_id = token_id;
    }

    public String getRoom_id() {
        return roomId;
    }

    public void setRoom_id(String room_id) {
        this.roomId = room_id;
    }
}
