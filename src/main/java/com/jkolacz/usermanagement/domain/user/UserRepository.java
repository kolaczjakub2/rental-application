package com.jkolacz.usermanagement.domain.user;

public interface UserRepository {
    void save(User user);

    boolean existsWithLogin(String login);
}
