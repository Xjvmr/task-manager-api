package br.com.JoaoVictor.todolist.task;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private ITaskRepository taskRepository;

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request) {
        var idUser = request.getAttribute("idUser");
        taskModel.setIdUser((UUID)  idUser);
        var currentDate = LocalDate.now();

        if (currentDate.isAfter(ChronoLocalDate.from(taskModel.getStartAT())) || currentDate.isAfter(ChronoLocalDate.from(taskModel.getStartAT())) ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("a data de inicio deve ser maior que a data atual");
        }
        if (currentDate.isAfter(ChronoLocalDate.from(taskModel.getEndtAT())) ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("a data de inicio deve ser menor que a data de termino");
        }


        var task = this.taskRepository.save(taskModel);
        return ResponseEntity.status(HttpStatus.OK).body(taskModel);
    }

    public void list(HttpServletRequest request){
        var idUser = request.getAttribute("idUser");
        this.taskRepository.findByIdUser((UUID)idUser);
    }
}
