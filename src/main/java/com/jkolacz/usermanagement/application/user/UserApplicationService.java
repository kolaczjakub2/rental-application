package com.jkolacz.usermanagement.application.user;

import com.jkolacz.usermanagement.domain.user.User;
import com.jkolacz.usermanagement.domain.user.UserFactory;
import com.jkolacz.usermanagement.domain.user.UserRepository;

public class UserApplicationService {
    private final UserRepository userRepository;
    private final UserFactory userFactory;

    UserApplicationService(UserRepository userRepository, UserFactory userFactory) {
        this.userRepository = userRepository;
        this.userFactory = userFactory;
    }

    public void register(UserDto userDto) {
        User user = userFactory.create(userDto.getLogin(), userDto.getName(), userDto.getLastName());

        userRepository.save(user);
    }
}
