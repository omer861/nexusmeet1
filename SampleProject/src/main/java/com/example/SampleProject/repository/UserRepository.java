package com.example.SampleProject.repository;



import com.example.SampleProject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <User,Long> {
    User findFirstByEmail(String email);
}
