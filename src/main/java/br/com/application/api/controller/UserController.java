package br.com.application.api.controller;

import br.com.application.api.dto.UserDTO;
import br.com.application.api.dto.UserRequestDTO;
import br.com.application.api.dto.UserResponseDTO;
import br.com.application.api.service.UserService;
import br.com.application.api.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public UserResponseDTO login(@RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userRequestDTO.getLogin(), userRequestDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        if (authentication.isAuthenticated()) {
            String token = jwtTokenUtil.generateToken(userRequestDTO.getLogin());

            userResponseDTO = UserResponseDTO.builder()
                    .login(userRequestDTO.getLogin())
                    .token(token)
                    .build();

            return userResponseDTO;
        }

        return userResponseDTO;
    }

    @RequestMapping(value = "/principal", method = RequestMethod.GET)
    public UserDTO principal(Principal principal) {
        UserDTO userDTO = userService.byLogin(principal.getName());
        return userDTO;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Page<UserDTO> list(Pageable pageable) {
        return userService.list(pageable);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserDTO getById(@PathVariable("id") Long id) {
        return userService.byId(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public UserDTO add(@RequestBody UserDTO userDTO) {
        return userService.save(userDTO);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public UserDTO update(@PathVariable("id") Long id, @RequestBody UserDTO userDTO) {
        UserDTO.UserDTOBuilder userDTOBuilder = userDTO.toBuilder();

        userDTO = userDTOBuilder
                .id(id)
                .build();

        return userService.save(userDTO);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        userService.delete(id);
    }

}
