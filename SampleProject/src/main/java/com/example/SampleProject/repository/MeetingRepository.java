package com.example.SampleProject.repository;



import com.example.SampleProject.entity.Meeting;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface MeetingRepository extends CrudRepository<Meeting, Integer> {
    Optional<Meeting> findByRoomId(String room_id);

}
