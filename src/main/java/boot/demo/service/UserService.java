package boot.demo.service;

import boot.demo.configuration.security.DemoUserDetails;
import boot.demo.domain.dto.UserView;
import boot.demo.domain.mapper.UserEditMapper;
import boot.demo.domain.mapper.UserViewMapper;
import boot.demo.repository.UserRepo;
import boot.demo.request.CreateUserRequest;
import boot.demo.request.UpdateUserRequest;
import boot.demo.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;
    private final UserEditMapper userEditMapper;
    private final UserViewMapper userViewMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserView create(CreateUserRequest request) {
        User user = userEditMapper.create(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user = userRepo.save(user);
        return userViewMapper.toUserView(user);
    }

    @Transactional
    public UserView update(Long id, UpdateUserRequest request) {
        Optional<User> user1 = userRepo.findById(id);
        User user = user1.get();
        userEditMapper.update(request, user);
        user = userRepo.save(user);
        return userViewMapper.toUserView(user);
    }

    @Transactional
    public UserView upsert(CreateUserRequest request) {
        Optional<User> optionalUser = userRepo.findByUsername(request.getUsername());

        if (!optionalUser.isPresent()) {
            return create(request);
        } else {
            UpdateUserRequest updateUserRequest = new UpdateUserRequest();
            updateUserRequest.setFullName(request.getFullName());
            return update(optionalUser.get().getId(), updateUserRequest);
        }
    }

    @Transactional
    public UserView delete(Long id) {
        Optional<User> user1 = userRepo.findById(id);
        User user = user1.get();
        user.setUsername(user.getUsername().replace("@", String.format("_%s@", user.getId())));
        user.setEnabled(false);
        user = userRepo.save(user);
        return userViewMapper.toUserView(user);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new DemoUserDetails(userRepo
                .findByUsername(username).get());
    }

    public boolean usernameExists(String username) {
        return userRepo.findByUsername(username).isPresent();
    }

    public UserView getUser(long id) {
        return userViewMapper.toUserView(userRepo.findById(id).get());
    }


    public List<UserView> getAll() {
        return userRepo.findAll().stream().map( e -> userViewMapper.toUserView(e)).collect(Collectors.toList());
    }
}
