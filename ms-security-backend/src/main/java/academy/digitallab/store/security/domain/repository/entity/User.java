package academy.digitallab.store.security.domain.repository.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@EntityListeners(AuditingEntityListener.class)
@Data
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "tbl_users")
public class User {

    @Id
    @Column(name = "user_name")
    private String username;
    private  String password;
    private String email;
    private Boolean enabled;
    private  String status;



    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Authority> authorities;



    @CreatedBy
    @Column(name = "created_user", nullable = false,
            updatable = false)
    private String createdBy;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", nullable = false,
            updatable = false)
    private Date createdDate;

    @LastModifiedBy
    @Column(name = "updated_user")
    private String updatedBy;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_date")
    private Date updatedDate;


    @Version
    @Column(name = "row_version")
    @JsonIgnore
    private Long rowVersion;

    @Transient
    private String confirmPassword;

    public Boolean isEnabled(){
        return this.enabled;
    }

}
