package academy.digitallab.store.security.domain.service;

public interface RedisService {
    public void addToken( String token);
    public boolean existToken( String token);
}
