package io.github.oclay1st.vrs.modules.security.user.domain.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.oclay1st.vrs.modules.common.exceptions.ValidationException;
import io.github.oclay1st.vrs.modules.security.user.domain.command.RegisterUserCommand;
import io.github.oclay1st.vrs.modules.security.user.domain.repository.UserRepository;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    /**
     * Create a new user from a given data
     * Validate user doesn't exists
     *
     * @param command a {@link RegisterUserCommand}
     */
    @Transactional
    public void register(RegisterUserCommand command) {
        if (userRepository.existByUsername(command.getUsername())) {
            throw new ValidationException("User already exists with the given username: " + command.getUsername());
        }
        command.encodePassword(passwordEncoder);
        userRepository.create(command);
    }

}
