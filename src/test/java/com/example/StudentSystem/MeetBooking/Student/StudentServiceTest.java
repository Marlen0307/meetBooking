package com.example.StudentSystem.MeetBooking.Student;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;
    private StudentService underTest;

    @BeforeEach
    void setUp() {
        underTest = new StudentService(studentRepository);
    }


    @Test
    void canGetStudents() {
        //when
        underTest.getStudents();

        //then
        verify(studentRepository).findAll();
    }

    @Test
    void canGetStudent() {
        //given
        Student student = new Student(
                1L,
                "Marlen",
                "Hima",
                LocalDate.of(2000, 07, 03),
                "marlen.hima@gmail.com"
        );
        given(studentRepository.findById(student.getId())).willReturn(Optional.of(student));


        //when
        underTest.getStudent(student.getId());

        //then
        ArgumentCaptor<Long> studentIdArgumentCaptor = ArgumentCaptor.forClass(Long.class);

        verify(studentRepository).findById(studentIdArgumentCaptor.capture());

        Long capturedStudentId = studentIdArgumentCaptor.getValue();

        assertThat(capturedStudentId).isEqualTo(student.getId());
    }

    @Test
    void throwsWhenUserIsNotFoundBYId() {
        //given
        Student student = new Student(
                1L,
                "Marlen",
                "Hima",
                LocalDate.of(2000, 07, 03),
                "marlen.hima@gmail.com"
        );

        given(studentRepository.findById(student.getId())).willReturn(Optional.empty());

        //when
        //then
        assertThatThrownBy(()->underTest.getStudent(student.getId())).isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Student with id "+ student.getId() + "not found");
    }



    @Test
    void canAddNewStudent() {
        //given
        Student student = new Student(
                "Marlen",
                "Hima",
                LocalDate.of(2000, 07, 03),
                "marlen.hima@gmail.com"
        );
        //when
        underTest.addNewStudent(student);

        //then
        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);
        verify(studentRepository).save(studentArgumentCaptor.capture());

        Student capturedStudent = studentArgumentCaptor.getValue();

        assertThat(capturedStudent).isEqualTo(student);

    }

    @Test
    void throwsWhenEmailTaken() {
        //given
        Student student = new Student(
                "Marlen",
                "Hima",
                LocalDate.of(2000, 07, 03),
                "marlen.hima@gmail.com"
        );

        given(studentRepository.findStudentByEmail(student.getEmail())).willReturn(Optional.of(student));


        //when
        //then
        assertThatThrownBy(()->underTest.addNewStudent((student))).isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Email taken");

        verify(studentRepository, never()).save(any());


    }

    @Test
    void canDeleteStudent() {
        //given
        Student student = new Student(
                1L,
                "Marlen",
                "Hima",
                LocalDate.of(2000, 07, 03),
                "marlen.hima@gmail.com"
        );
        given(studentRepository.existsById(student.getId())).willReturn(true);

        //when
        underTest.deleteStudent(student.getId());

        //then
        ArgumentCaptor<Long> studentIdArgumentCaptor = ArgumentCaptor.forClass(Long.class);

        verify(studentRepository).deleteById(studentIdArgumentCaptor.capture());

        Long capturedStudentId = studentIdArgumentCaptor.getValue();

        assertThat(capturedStudentId).isEqualTo(student.getId());
    }

    @Test
    void throwsWhenUserDoesNotExist() {
        //given
        Student student = new Student(
                1L,
                "Marlen",
                "Hima",
                LocalDate.of(2000, 07, 03),
                "marlen.hima@gmail.com"
        );

        given(studentRepository.existsById(student.getId())).willReturn(false);

        //when
        //then
        assertThatThrownBy(()->underTest.deleteStudent(student.getId())).isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Student with id " + student.getId() + " does not exist");

        verify(studentRepository, never()).deleteById(any());
    }

    @Test
    void canGetStudentToUpdateById(){

        //given
        Student student = new Student(
                1L,
                "Marlen",
                "Hima",
                LocalDate.of(2000, 07, 03),
                "marlen.hima@gmail.com"
        );

        given(studentRepository.findById(student.getId())).willReturn(Optional.of(student));

        //when
        underTest.updateStudent(student.getId(), student.getFirstname(), student.getLastname(),student.getEmail(), student.getDob());

        //then
        ArgumentCaptor<Long> studentIdArgumentCaptor = ArgumentCaptor.forClass(Long.class);

        verify(studentRepository).findById(studentIdArgumentCaptor.capture());

        Long capturedStudentId = studentIdArgumentCaptor.getValue();

        assertThat(capturedStudentId).isEqualTo(student.getId());
    }

    @Test
    void throwsWhenUserToUpdateDoesNotExist() {
        //given
        Student student = new Student(
                1L,
                "Marlen",
                "Hima",
                LocalDate.of(2000, 07, 03),
                "marlen.hima@gmail.com"
        );

        given(studentRepository.findById(student.getId())).willReturn(Optional.empty());

        //when
        //then
        assertThatThrownBy(()->underTest.updateStudent(student.getId(), student.getFirstname(), student.getLastname(),student.getEmail(), student.getDob()))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("The student with "+ student.getId() + " id does not exist");
    }

    @Test
    void canFindUserByEmail(){
        //given
        Student student = new Student(
                1L,
                "Marlen",
                "Hima",
                LocalDate.of(2000, 07, 03),
                "marlen.hima@gmail.com"
        );

        String newEmail = "hima.marlen@yahoo.com";

        given(studentRepository.findById(student.getId())).willReturn(Optional.of(student));

        //when
        underTest.updateStudent(student.getId(), student.getFirstname(), student.getLastname(),newEmail, student.getDob());

        //then
        //then
        ArgumentCaptor<String> studentEmailCaptor = ArgumentCaptor.forClass(String.class);

        verify(studentRepository).findStudentByEmail(studentEmailCaptor.capture());

        String capturedEmail = studentEmailCaptor.getValue();

        assertThat(capturedEmail).isEqualTo("hima.marlen@yahoo.com");

    }

    @Test
    void throwsWhenEmailToBeUpdatedIsTaken() {
        //given
        Student student = new Student(
                "Marlen",
                "Hima",
                LocalDate.of(2000, 07, 03),
                "marlen.hima@gmail.com"
        );

        String newEmail = "hima.marlen@yahoo.com";

        given(studentRepository.findById(student.getId())).willReturn(Optional.of(student));
        given(studentRepository.findStudentByEmail(newEmail)).willReturn(Optional.of(student));


        //when
        //then
        assertThatThrownBy(()->underTest.updateStudent(student.getId(), student.getFirstname(), student.getLastname(),newEmail, student.getDob()))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Email Taken");

    }


}