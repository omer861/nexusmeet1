package com.example.SampleProject.repository;


import com.example.SampleProject.entity.Meeting;
import com.example.SampleProject.entity.MeetingAttendees;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface MeetingAttendeesRepository extends CrudRepository<MeetingAttendees, Integer> {
    Optional<MeetingAttendees> findByEmailAndMeetingId(String email, long meeting_id);
    List<MeetingAttendees> findByEmailOrderByIdDesc(String email);
    List<MeetingAttendees> findByMeetingId(Integer meeting_id);
}
