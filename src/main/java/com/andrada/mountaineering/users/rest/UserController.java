package com.andrada.mountaineering.users.rest;

import com.andrada.mountaineering.exceptions.NoUserNameException;
import com.andrada.mountaineering.users.dto.UserContract;
import com.andrada.mountaineering.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }



    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserContract> getAllUsers(){
        return userService.getAllUsers().stream()
                .map(UserContract::of)
                .collect(Collectors.toList());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserContract createNewUser(@RequestBody UserContract user) throws NoUserNameException {
        return UserContract.of(userService.createNewUser(userService.contract2entity(user)));
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserContract getUser(@PathVariable long id) throws Exception{
        return UserContract.of(userService.getUser(id));
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserContract updateUser(@PathVariable long id, @RequestBody UserContract user) throws Exception{
        return UserContract.of(userService.updateUser(id, userService.contract2entity(user)));
    }

    @DeleteMapping(path = "/{id}")
    public void deleteUser(@PathVariable long id) throws Exception{
        userService.deleteUser(id);
    }
}
