package academy.digitallab.store.security.auth;

import lombok.Data;

@Data
public class AccountCredentials {
    private String username;
    private String password;
}
