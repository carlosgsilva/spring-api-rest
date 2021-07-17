package br.com.application.api.controller;

import br.com.application.api.dto.UserDTO;
import br.com.application.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Page<UserDTO> listUsers(Pageable pageable) {
        return userService.list(pageable);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserDTO getUserById(@PathVariable("id") Long id) {
        return userService.byId(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public UserDTO addUser(@RequestBody UserDTO userDTO) {
        return userService.save(userDTO);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public UserDTO updateUser(@PathVariable("id") Long id, @RequestBody UserDTO userDTO) {
        UserDTO.UserDTOBuilder userDTOBuilder = userDTO.toBuilder();

        userDTO = userDTOBuilder
                .id(id)
                .build();

        return userService.save(userDTO);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
    }

}
