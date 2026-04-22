package br.com.JoaoVictor.todolist.user;

import at.favre.lib.crypto.bcrypt.BCrypt;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.GregorianCalendar.BC;

@RestController
@RequestMapping("/users")
public class UserController {

    /*gerrencia UIrepository */
    @Autowired
    private IUserRepository userRepository;

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
