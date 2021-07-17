package br.com.application.api.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "users")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String login;
    @Column
    private String password;

}
