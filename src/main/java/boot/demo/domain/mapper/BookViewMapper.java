package boot.demo.domain.mapper;

import boot.demo.domain.dto.BookView;
import boot.demo.domain.model.Book;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", uses = ObjectIdMapper.class)
public abstract class BookViewMapper {

    private UserViewMapper userViewMapper;

    @Autowired
    public void setUserViewMapper(UserViewMapper userViewMapper) {
        this.userViewMapper = userViewMapper;
    }

    public abstract BookView toBookView(Book book);

    public abstract List<BookView> toBookView(List<Book> books);



}
