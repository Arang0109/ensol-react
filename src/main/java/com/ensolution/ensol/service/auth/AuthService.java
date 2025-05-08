package com.ensolution.ensol.service.auth;

import com.ensolution.ensol.common.util.CustomUserDetails;
import com.ensolution.ensol.entity.auth.User;
import com.ensolution.ensol.repository.auth.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username);

    if (user == null) {
      throw new UsernameNotFoundException(username + " not found");
    }

    return new CustomUserDetails(user);
  }
}
