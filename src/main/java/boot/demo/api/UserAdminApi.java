package boot.demo.api;

import boot.demo.domain.dto.UserView;
import boot.demo.domain.model.Role;
import boot.demo.request.CreateUserRequest;
import boot.demo.request.UpdateUserRequest;
import boot.demo.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@Tag(name = "UserAdmin")
@RestController @RequestMapping(path = "api/v1/users")
@RolesAllowed(Role.USER_ADMIN)
@RequiredArgsConstructor
public class UserAdminApi {

    private final UserService userService;

    @PostMapping
    public UserView create(@RequestBody @Valid CreateUserRequest request) {
        return userService.create(request);
    }

    @PutMapping("{id}")
    public UserView update(@PathVariable Long id, @RequestBody @Valid UpdateUserRequest request) {
        return userService.update( id, request);
    }

    @DeleteMapping("{id}")
    public UserView delete(@PathVariable Long id) {
        return userService.delete(id);
    }

    @GetMapping("{id}")
    public UserView get(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @GetMapping
    public List<UserView> getAll() {
        return userService.getAll();
    }

}
