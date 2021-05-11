package com.example.StudentSystem.MeetBooking.registration;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmailValidator implements Predicate<String> {
    private Pattern pattern;
    private Matcher matcher;
    private static final String EMAIL_REGEX =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public EmailValidator(){
        pattern = Pattern.compile(EMAIL_REGEX);
    }
    @Override
    public boolean test(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
