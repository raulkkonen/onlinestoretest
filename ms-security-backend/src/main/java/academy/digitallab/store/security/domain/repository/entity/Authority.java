package academy.digitallab.store.security.domain.repository.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@AllArgsConstructor @NoArgsConstructor
@Table(name = "tbl_authorities",
        uniqueConstraints = @UniqueConstraint(columnNames = {"role", "user_id"}))
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String role;

    @Column(name = "user_id")
    private String username;
}
