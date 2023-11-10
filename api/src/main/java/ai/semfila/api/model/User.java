package ai.semfila.api.model;


import ai.semfila.api.DTO.user.UserRequest;
import ai.semfila.api.DTO.user.UserRequestUpdate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "USERS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID= 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;

    @OneToMany(mappedBy = "user")
    private List<Commands> commands;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @Column(name = "phone", nullable = false, unique = true, length = 11)
    private String phone;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @Column(name= "create_at", nullable = false)
    private LocalDateTime createAt;

    @Column(name= "update_at", nullable = false)
    private LocalDateTime updateAt;

    @Column(name="deleted", nullable = false)
    private boolean deleted;

    public User(UserRequest userRequest) {
        this.name = userRequest.name();
        this.cpf = userRequest.cpf().replace(".","").replace("-","");
        this.birthday = userRequest.birthday();
        this.phone = userRequest.phone();
        this.deleted = false;
        this.updateAt = LocalDateTime.now();
        this.createAt = LocalDateTime.now();
    }

    public void update(UserRequestUpdate userRequestUpdate) {
        if(userRequestUpdate.name() != null){
            this.name= userRequestUpdate.name();
            this.updateAt = LocalDateTime.now();
        }

        if(userRequestUpdate.birthday()!= null){
            this.birthday= userRequestUpdate.birthday();
            this.updateAt = LocalDateTime.now();
        }
    }
}
