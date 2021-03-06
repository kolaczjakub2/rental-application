package com.jkolacz.usermanagement.domain.user;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@SuppressWarnings("PMD.UnusedPrivateField")
public class User {
    private final String login;
    private final Name name;

    User(String login, Name name) {
        this.login = login;
        this.name = name;
    }
}
