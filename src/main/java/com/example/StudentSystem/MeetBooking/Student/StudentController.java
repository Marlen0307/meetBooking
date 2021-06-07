package com.example.StudentSystem.MeetBooking.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/",allowedHeaders = {"*"})
@RestController
@RequestMapping(path = "api/v1/student") //Kerko
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    @GetMapping(path = "{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long studentId){
        return ResponseEntity.ok(studentService.getStudent(studentId));
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }
    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable("studentId") Long studentId,
                              @RequestParam(required = false) String firstname,
                              @RequestParam(required = false) String lastname,
                              @RequestParam(required = false) String email,
                              @RequestParam(required = false) String dob){
                LocalDate birthday = LocalDate.parse(dob);
                studentService.updateStudent(studentId,firstname, lastname, email, birthday);
    }
}
