package com.example.StudentSystem.MeetBooking.appUser;

import com.example.StudentSystem.MeetBooking.email.EmailSender;
import com.example.StudentSystem.MeetBooking.email.EmailService;
import com.example.StudentSystem.MeetBooking.registration.RegistrationService;
import com.example.StudentSystem.MeetBooking.registration.token.ConfirmationToken;
import com.example.StudentSystem.MeetBooking.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND = "User with email: %s not found";
    private final AppUserRepository appUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return appUserRepository.findByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException(String.format(USER_NOT_FOUND, email)));
    }
    public String signUpUser(AppUser appUser){

        //check if te user exists
        boolean userExists = appUserRepository.findByEmail(appUser.getEmail()).isPresent();

        //create the token
        String token = UUID.randomUUID().toString();


        if (userExists){

            //we get the existing user from the db
           UserDetails existentUser = loadUserByUsername(appUser.getEmail());

            System.out.println(existentUser.getUsername());
           if (existentUser.isEnabled()) {
               throw new IllegalStateException("Email already taken");
           }
            AppUser appUser1 = appUserRepository.getOne(appUser.getUsername());
            ConfirmationToken confirmationToken = new ConfirmationToken(
                    token,
                    LocalDateTime.now(),
                    LocalDateTime.now().plusMinutes(15),
                    appUser1
            );
            confirmationTokenService.saveConfirmationToken(confirmationToken);
            return token;
        }

        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);
        appUserRepository.save(appUser);

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return token;
    }
    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }
}
