package academy.digitallab.store.security.domain.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UtilCrypt {

    public static void main(String[] args) {
        BCryptPasswordEncoder pe= new BCryptPasswordEncoder();
        System.out.println("pas: "  + pe.encode("123456"));

    }
}