package io.github.oclay1st.vrs.modules.security.user.domain.command;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Objects;

public class RegisterUserCommand {

    private final String username;

    private String password;

    private final String name;

    private final String lastName;

    private final boolean isActive;

    private final LocalDateTime createdAt;

    private final LocalDateTime updateAt;

    public RegisterUserCommand(String password, String username, String name, String lastName, boolean isActive) {
        this.username = Objects.requireNonNull(username);
        this.password = Objects.requireNonNull(password);
        this.name = Objects.requireNonNull(name);
        this.lastName = Objects.requireNonNull(lastName);
        this.isActive = isActive;
        LocalDateTime current_time = LocalDateTime.now();
        this.createdAt = current_time;
        this.updateAt = current_time;
    }

    public boolean isActive() {
        return isActive;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void encodePassword(PasswordEncoder passwordEncoder) {
        password = passwordEncoder.encode(password);
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

}
