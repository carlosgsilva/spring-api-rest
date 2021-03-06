package br.com.application.api.dto;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class UserDTO {

    private Long id;
    private String name;
    private String login;
    private String password;

}
