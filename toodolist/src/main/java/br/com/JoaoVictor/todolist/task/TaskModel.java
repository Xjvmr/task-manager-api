package br.com.JoaoVictor.todolist.task;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;


import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "tb_task")
public class TaskModel {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String description;
    @Column(length = 100)
    private String title;
    private LocalDateTime startAT;
    private LocalDateTime endtAT;
    private String prioridade;

    private LocalDateTime creatAT;
    private UUID idUser;



}
