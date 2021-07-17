package br.com.application.api.dto;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class UserResponseDTO {

    private String login;
    private String token;

}
