package br.com.JoaoVictor.todolist.task;

import br.com.JoaoVictor.todolist.ultis.Ultils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    // Base URL: http://localhost:8080/tasks
    @Autowired
    private ITaskRepository taskRepository;

    // Endpoint: POST http://localhost:8080/tasks/create
    // O que faz: cria uma nova tarefa para o usuario autenticado e valida datas.
    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody TaskModel taskModel, HttpServletRequest request) {
        var idUser = request.getAttribute("idUser");
        taskModel.setIdUser((UUID)  idUser);
        var currentDate = LocalDate.now();
        var startDate = taskModel.getStartAT().toLocalDate();
        var endDate = taskModel.getEndtAT().toLocalDate();

        if (startDate.isBefore(currentDate)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("a data de inicio deve ser maior que a data atual");
        }
        if (endDate.isBefore(startDate)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("a data de inicio deve ser menor que a data de termino");
        }


        var task = this.taskRepository.save(taskModel);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }
    // Endpoint: GET http://localhost:8080/tasks/
    // O que faz: lista todas as tarefas do usuario autenticado.
    @GetMapping ("/")
    public List<TaskModel> list(HttpServletRequest request){
        var idUser = request.getAttribute("idUser");
        return this.taskRepository.findByIdUser((UUID)idUser);
    }
    // Endpoint: PUT http://localhost:8080/tasks/{id}
    // atualiza os dados de uma tarefa existente pelo id.
    @PutMapping ("/{id}")
    public ResponseEntity<Object> update (@RequestBody TaskModel taskModel, HttpServletRequest  request,@PathVariable UUID id){

        var task = this.taskRepository.findById(id).orElse(null);
        if (task == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("tarefa não encontrada");
        }
        var idUser = request.getAttribute("idUser");
        if (!task.getIdUser().equals(idUser)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario não tem permissão para alterar essa tarefa");
        }

        if (!task.getIdUser().equals((UUID) idUser)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("usuario nao tem permissao para alterar essa tarefa");
        }

        Ultils.copyNonNullProperties(taskModel, task);
        var taskUpdated = this.taskRepository.save(task);
        return ResponseEntity.ok().body(taskUpdated);

    }
}
