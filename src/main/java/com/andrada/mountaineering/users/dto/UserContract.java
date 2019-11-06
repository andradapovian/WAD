package com.andrada.mountaineering.users.dto;

import com.andrada.mountaineering.users.model.User;
import com.andrada.mountaineering.users.roles.model.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserContract implements Serializable {

    private long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Set<String> roles;

    public static UserContract of(User user) {
        UserContract target = new UserContract();
        BeanUtils.copyProperties(user, target, "password");
        target.setRoles(user.getRoles().stream()
                .map(UserRole::getRole)
                .collect(Collectors.toSet()));
        return target;
    }

}
