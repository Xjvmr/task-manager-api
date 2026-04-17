package br.com.JoaoVictor.todolist.user;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data/*lombook*/
@Entity(name = "tb_users")/*tabela*/


public class UserModel {
    @Id /*refencia primary key*/
    @GeneratedValue(generator = "UUID")/*id altomantico*/
    private UUID id;

    @Column(unique = true)
    private String nome;
    private String userName;
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;
}



