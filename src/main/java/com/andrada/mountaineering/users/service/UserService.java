package com.andrada.mountaineering.users.service;


import com.andrada.mountaineering.events.rest.EventController;
import com.andrada.mountaineering.exceptions.EntityNotFoundException;
import com.andrada.mountaineering.exceptions.NoUserNameException;
import com.andrada.mountaineering.users.dto.UserContract;
import com.andrada.mountaineering.users.model.User;
import com.andrada.mountaineering.users.repository.UserRepository;
import com.andrada.mountaineering.users.roles.model.UserRole;
import com.andrada.mountaineering.users.roles.repository.UserRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(EventController.class);

    @Autowired
    public UserService(UserRepository userRepository,
                       UserRoleRepository userRoleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository=userRepository;
        this.userRoleRepository=userRoleRepository;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User createNewUser(User user){
        Optional<UserRole> role = userRoleRepository.findRoleByName("ROLE_VIEWER");
        user.getRoles().add(role.get());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public User getUser(long id) throws EntityNotFoundException{
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found at ID "+ id));
    }

    public User findByUsername(String username) throws EntityNotFoundException {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User with username " + username + " not found"));
    }

    public User updateUser(long id, User user) throws EntityNotFoundException{
        User dbUser=userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found at ID "+ id));
        if(Objects.isNull(dbUser))
            return null;
        dbUser.setRoles(user.getRoles());
        dbUser.setEmail(user.getEmail());
        dbUser.setPassword(user.getPassword());
        dbUser.setFirstName(user.getFirstName());
        dbUser.setLastName(user.getLastName());
        return userRepository.save(dbUser);
    }

    public void deleteUser (long id) throws EntityNotFoundException{
        User dbUser = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found at ID "+ id));
        if (Objects.isNull(dbUser))
            return;
        userRepository.delete(dbUser);
    }

    public User contract2entity(UserContract userContract) throws NoUserNameException {
        User target = new User();
        if (userContract.getUsername() == null)
            throw new NoUserNameException("Must introduce username");
        BeanUtils.copyProperties(userContract, target, "roles");
        return target;
    }
}