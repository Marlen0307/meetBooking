
package com.example.StudentSystem.MeetBooking.Student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
          Student marlen =   new Student(
                    "Marlen",
                    "Hima",
                    LocalDate.of(2000, Month.JULY,03),
                    "marlen.hima@gmail.com"
            );
           Student ergi = new Student(
                    "Ergi",
                    "Gorishti",
                    LocalDate.of(1999, Month.OCTOBER,20),
                    "ergi.gorishti@gmail.com"
            );
            repository.saveAll(List.of(marlen,ergi));

        };
    }
}

