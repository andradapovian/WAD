package com.andrada.mountaineering.users.service;


import com.andrada.mountaineering.users.model.User;
import com.andrada.mountaineering.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User createNewUser(User user){
        return userRepository.save(user);
    }

    public User getUser(long id){
        return userRepository.findById(id).orElse(null);
    }

    public User updateUser(long id, User user){
        User dbUser=userRepository.findById(id).orElse(null);
        if(Objects.isNull(dbUser))
            return null;
        dbUser.setRoles(user.getRoles());
        dbUser.setEmail(user.getEmail());
        dbUser.setPassword(user.getPassword());
        dbUser.setFirstName(user.getFirstName());
        dbUser.setLastName(user.getLastName());
        return userRepository.save(dbUser);
    }

    public void deleteUser (long id){
        User dbUser = userRepository.findById(id).orElse(null);
        if (Objects.isNull(dbUser))
            return;
        userRepository.delete(dbUser);
    }
}
