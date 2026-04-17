package br.com.JoaoVictor.todolist.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IUserRepository extends JpaRepository <UserModel, UUID> {
//    faz select e busca nome de usuario
    UserModel findByuserName(String userName);
}