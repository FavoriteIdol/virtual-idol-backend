package com.outsider.virtual.user.command.infrastructure.service;

import com.outsider.virtual.user.command.domain.aggregate.User;
import com.outsider.virtual.user.command.domain.repository.UserCommandRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

// JPA Repository


// Service class
@Service
public class CustomUserService implements UserDetailsService {

    private final UserCommandRepository userRepository;

    public CustomUserService(UserCommandRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(userEmail);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + userEmail));
        checkWithdrawalStatus(user);
        return new CustomUserDetail(user);
    }

    public void checkWithdrawalStatus(User user) {
        if (user.getWithdrawal()) {
            user.setWithdrawal(false);
            userRepository.save(user);
        }
    }
}
