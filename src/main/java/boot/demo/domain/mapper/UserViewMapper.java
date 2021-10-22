package boot.demo.domain.mapper;

import boot.demo.domain.dto.UserView;
import boot.demo.domain.model.User;
import boot.demo.repository.UserRepo;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ObjectIdMapper.class})
public abstract class UserViewMapper {

    @Autowired
    private UserRepo userRepo;

    public abstract UserView toUserView(User user);

    public abstract List<UserView> toUserView(List<User> users);

    public UserView toUserViewById(Long id) {
        if (id == null) {
            return null;
        }
        return toUserView(userRepo.findById(id).get());
    }

}
