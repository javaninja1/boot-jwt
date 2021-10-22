package boot.demo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role {

    public static final String USER_ADMIN = "USER_ADMIN";
    public static final String BOOK_ADMIN = "BOOK_ADMIN";


    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;


    public Role(String role) {
        this.name = role;
    }
}
