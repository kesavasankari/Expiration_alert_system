package com.expirtion_alert.service;

import com.expirtion_alert.dto.UserRequest;
import com.expirtion_alert.entity.User;
import com.expirtion_alert.exception.EmailAlreadyExists;
import com.expirtion_alert.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo repo;
    private final PasswordEncoder encoder;

    @Override
    public String register( UserRequest request) {

        if(repo.existsByEmail(request.email())){
            throw new EmailAlreadyExists("The Email Already exists");
        }

        User user=User.builder()
                .email(request.email())
                .password(encoder.encode(request.password()))
                .build();

        repo.save(user);

        return "User Registered Successfully";
    }
}
