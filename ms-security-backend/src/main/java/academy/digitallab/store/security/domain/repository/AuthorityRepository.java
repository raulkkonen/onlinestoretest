package academy.digitallab.store.security.domain.repository;
import academy.digitallab.store.security.domain.repository.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority,Long> {
    public List<Authority> findByUsername(String username);
}