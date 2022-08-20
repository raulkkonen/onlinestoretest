package academy.digitallab.store.security.api;



import academy.digitallab.store.security.auth.util.Constant;
import academy.digitallab.store.security.config.RedisCFG;
import academy.digitallab.store.security.domain.dto.TokenDto;
import academy.digitallab.store.security.domain.repository.entity.User;
import academy.digitallab.store.security.domain.service.RedisService;
import academy.digitallab.store.security.domain.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Slf4j
@RequestMapping(value = "/security")
@RestController
@RequiredArgsConstructor
public class LoginRest {
    private final RedisService redisService;
    private  final UserServiceImpl userService;
    private final RedisCFG redisCFG;


    @RequestMapping(value="/login" , method = RequestMethod.POST)
    public ResponseEntity<TokenDto> getUser(HttpServletResponse res){
        log.debug("login - inicio");

        TokenDto tokenBean = new TokenDto();
        String token =res.getHeader( Constant.TOKEN_HEADER_STRING );
        if (token !=null){

            tokenBean.setToken(token);
            return new ResponseEntity<>(tokenBean, HttpStatus.OK);
        }else{
            return new ResponseEntity<TokenDto>(tokenBean, HttpStatus.UNAUTHORIZED);
        }

    }

    @RequestMapping(value="/welcome" , method = RequestMethod.GET)
    public String welcome(){
        return "welcome";
    }

    @PostMapping(value="/users" )
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){

        log.info("Creating User : {}", user);


        userService.createUser(user);

        user.setConfirmPassword(null);
        user.setPassword(null);


        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @RequestMapping(value = "/finalizar", method = RequestMethod.POST)
    public ResponseEntity<String> finalizar(HttpServletRequest request){
        String token  =  request.getHeader(Constant.TOKEN_HEADER_STRING);
        if (token != null){
            redisService.addToken(token);
            return new ResponseEntity<>("Logout", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Not Access", HttpStatus.UNAUTHORIZED);
        }
    }
    @RequestMapping(value = "/redis", method = RequestMethod.GET)
    public ResponseEntity<RedisCFG> redis(){
        return new ResponseEntity<>( redisCFG, HttpStatus.OK );
    }


}
