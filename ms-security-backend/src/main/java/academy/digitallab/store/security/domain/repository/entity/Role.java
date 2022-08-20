package academy.digitallab.store.security.domain.repository.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "tbl_role")
public class Role {

    @Id
    private String role;
    private String status;

}
