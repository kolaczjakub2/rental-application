package com.jkolacz.usermanagement.application.user;

import com.jkolacz.usermanagement.domain.user.UserFactory;
import com.jkolacz.usermanagement.domain.user.UserRepository;

public class UserApplicationServiceFactory {
    public UserApplicationService userApplicationService(UserRepository userRepository) {
        return new UserApplicationService(userRepository, new UserFactory(userRepository));
    }
}
