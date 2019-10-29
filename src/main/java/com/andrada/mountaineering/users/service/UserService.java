package com.andrada.mountaineering.users.service;


import com.andrada.mountaineering.events.rest.EventController;
import com.andrada.mountaineering.exceptions.EntityNotFoundException;
import com.andrada.mountaineering.users.model.User;
import com.andrada.mountaineering.users.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    private UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(EventController.class);

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

    public User getUser(long id) throws EntityNotFoundException{
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public User updateUser(long id, User user) throws EntityNotFoundException{
        User dbUser=userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
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
        User dbUser = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (Objects.isNull(dbUser))
            return;
        userRepository.delete(dbUser);
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ModelAndView handleEmployeeNotFoundException(HttpServletRequest request, Exception ex) {
        logger.error("Requested URL=" + request.getRequestURL());
        logger.error("Exception Raised=" + ex);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", ex);
        modelAndView.addObject("url", request.getRequestURL());

        modelAndView.setViewName("error");
        return modelAndView;
    }
}
