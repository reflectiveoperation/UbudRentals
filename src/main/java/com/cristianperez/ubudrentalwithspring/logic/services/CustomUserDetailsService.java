package com.cristianperez.ubudrentalwithspring.logic.services;

import com.cristianperez.ubudrentalwithspring.logic.interfaces.CustomerRepository;
import com.cristianperez.ubudrentalwithspring.logic.models.Role;
import com.cristianperez.ubudrentalwithspring.logic.models.Token;
import com.cristianperez.ubudrentalwithspring.logic.models.Users;
import com.cristianperez.ubudrentalwithspring.logic.interfaces.UsersRepository;
import com.cristianperez.ubudrentalwithspring.presentation.web.model.NewUser;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> optionalUsers = usersRepository.findByFirstName(username);

        optionalUsers
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return optionalUsers
                .map(CustomUserDetails::new).get();
    }

    public Users registerUser(NewUser newWebUser) {
        Users newRegisteredUser = new Users();
        newRegisteredUser.setFirstName(newWebUser.getFirstName());
        newRegisteredUser.setLastName(newWebUser.getLastName());
        newRegisteredUser.setEmail(newWebUser.getEmail());
        newRegisteredUser.setRoles(getRoles());
        newRegisteredUser.setPassword(passwordEncoder.encode(newWebUser.getPassword()));
        return usersRepository.save(newRegisteredUser);
    }

    private Set<Role> getRoles() {
        Set<Role> roles;
        Role role = new Role();
        role.setRole("ADMIN");
        roles = Collections.singleton(role);
        return roles;
    }

    public Token createTokenForUser() {
        Token token = new Token();
        token.setTokenCode(generateTokenForUser());
        customerRepository.saveTokenInDatabase(token.getTokenCode());
        return token;
    }

    private String generateTokenForUser() {
        return RandomStringUtils.randomAlphanumeric(8);
    }


    public Token validateApiToken(String apiToken) {
        return customerRepository.validateApiToken(apiToken);
    }
}
