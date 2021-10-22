package boot.demo.api;

import boot.demo.domain.dto.BookView;
import boot.demo.domain.model.Role;
import boot.demo.request.EditBookRequest;
import boot.demo.service.BookService;
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

@Tag(name = "Book")
@RestController @RequestMapping(path = "api/v1/books")
@RequiredArgsConstructor
public class BookApi {

    private final BookService bookService;

    @RolesAllowed(Role.BOOK_ADMIN)
    @PostMapping
    public BookView create(@RequestBody @Valid EditBookRequest request) {
        return bookService.create(request);
    }

    @RolesAllowed(Role.BOOK_ADMIN)
    @PutMapping("{id}")
    public BookView edit(@PathVariable Long id, @RequestBody @Valid EditBookRequest request) {
        return bookService.update(id, request);
    }

    @RolesAllowed(Role.BOOK_ADMIN)
    @DeleteMapping("{id}")
    public BookView delete(@PathVariable Long id) {
        return bookService.delete(id);
    }

    @GetMapping("{id}")
    public BookView get(@PathVariable Long id) {
        return bookService.getBook(id);
    }




}
