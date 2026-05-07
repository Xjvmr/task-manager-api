package br.com.JoaoVictor.taskManeger.user;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    // Base URL: http://localhost:8080/users

    /*gerrencia UIrepository */
    @Autowired
    private IUserRepository userRepository;

    // Endpoint: POST http://localhost:8080/users/cadastro
    // O que faz: cadastra um novo usuario, valida nome unico e criptografa senha.
    @PostMapping("/cadastro")
    //  ResponseEntity objeto de retorno erro/ sucesso spring
    public ResponseEntity cadastro (@RequestBody UserModel userModel){
      var user = this.userRepository.findByuserName(userModel.getUserName());
      if (user != null){
          System.out.println("usuario: "+userModel.getUserName() +" ja existe");
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("usuario ja existe");
        }
        //criptografia de senha
      var passwordHashered=BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());
      userModel.setPassword(passwordHashered);
        var userCreated = this.userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body("usuario: "+userModel.getUserName() +"criado  com sucesso");
    }
}
