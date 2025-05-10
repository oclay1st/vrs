package io.github.oclay1st.vrs.modules.security.auth.infrastructure.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.github.oclay1st.vrs.modules.security.auth.infrastructure.repository.UserSecurityRepository;

@Service
public class UserSecurityService implements UserDetailsService {

    private final UserSecurityRepository userSecurityRepository;

    public UserSecurityService(UserSecurityRepository userSecurityRepository) {
        this.userSecurityRepository = userSecurityRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userSecurityRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(""));
    }

}
