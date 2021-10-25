package boot.demo.domain.dto;

import boot.demo.domain.model.Role;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserView {

    private Long id;

    private String username;
    private String fullName;

    private Set<Role> roles = new HashSet<>();

}
