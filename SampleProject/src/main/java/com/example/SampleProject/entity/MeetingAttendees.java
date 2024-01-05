package com.example.SampleProject.entity;


import jakarta.persistence.*;

@Entity

public class MeetingAttendees {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String email;
    @Column(name="meeting_id")
    private Integer meetingId;

    private int join_status;


    public int getJoin_status() {
        return join_status;
    }

    public void setJoin_status(int join_status) {
        this.join_status = join_status;
    }

    public String getEmail() {
        return email;
    }

    public Integer getMeeting_id() {
        return meetingId;
    }

    public void setMeeting_id(Integer meeting_id) {
        this.meetingId = meeting_id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
