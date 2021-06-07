package com.example.StudentSystem.MeetBooking.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    public final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public Student getStudent(Long id){
        return studentRepository.findById(id)
                 .orElseThrow(()->new IllegalStateException("Student with id "+ id + "not found"));
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()){
            throw new IllegalStateException("Email taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists){
            throw new IllegalStateException("Student with id " + studentId + " does not exist");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String firstname, String lastname, String email, LocalDate dob) {
        Student student = studentRepository.findById(studentId).orElseThrow(()->
                new IllegalStateException("The student with "+ studentId + " id does not exist"));
        if(firstname !=null && firstname.length() > 0 && !Objects.equals(firstname, student.getFirstname())){
            student.setFirstname(firstname);
        }
        if(lastname !=null && lastname.length() > 0 && !Objects.equals(lastname, student.getLastname())){
            student.setLastname(lastname);
        }
        if(email != null && email.length() > 0 && !Objects.equals(email, student.getEmail())){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()){


                throw new IllegalStateException("Email Taken");
            }
            student.setEmail(email);
        }
        if (dob!= null){
            student.setDob(dob);
        }
    }
}
