package com.example.StudentSystem.MeetBooking.Student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldCheckIfStudentIsPresentByEmail() {
        //given
        String email = "marlen.hima@gmail.com";
        Student student = new Student(
                "Marlen",
                "Hima",
                LocalDate.of(2000,07,03),
                email
        );
        underTest.save(student);

        //when
        Optional <Student> optionalStudent = underTest.findStudentByEmail(email);

        //then
        assertThat(optionalStudent).isPresent();
    }

    @Test
    void itShouldCheckIfStudentIsNotPresentByEmail() {
        //given
        String email = "marlen.hima@gmail.com";

        //when
        Optional <Student> optionalStudent = underTest.findStudentByEmail(email);

        //then
        assertThat(optionalStudent).isNotPresent();
    }
}