package com.example.StudentSystem.MeetBooking.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository //Kerko
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email);

//    @Query("SELECT case when count(s) > 0 THEN TRUE ELSE FALSE END FROM Student s Where s.email = ?1")
//    Boolean getOne(String email);
}
