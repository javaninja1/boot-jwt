package boot.demo.service;

import boot.demo.domain.dto.BookView;
import boot.demo.request.EditBookRequest;
import boot.demo.domain.mapper.BookEditMapper;
import boot.demo.domain.mapper.BookViewMapper;
import boot.demo.domain.model.Book;
import boot.demo.repository.BookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepo bookRepo;
    private final BookEditMapper bookEditMapper;
    private final BookViewMapper bookViewMapper;

    @Transactional
    public BookView create(EditBookRequest request) {
        Book book = bookEditMapper.create(request);
        book = bookRepo.save(book);
        return bookViewMapper.toBookView(book);
    }

    @Transactional
    public BookView update(Long id, EditBookRequest request) {
        Book book = bookRepo.findById(id).get();
        bookEditMapper.update(request, book);
        book = bookRepo.save(book);
        return bookViewMapper.toBookView(book);
    }


    @Transactional
    public BookView delete(Long id) {
        Book book = bookRepo.findById(id).get();

        bookRepo.delete(book);

        return bookViewMapper.toBookView(book);
    }

    public BookView findById(Long id) {
        Book book = bookRepo.findById(id).get();
        return bookViewMapper.toBookView(book);
    }

    public List<BookView> getBooks(Iterable<Long> ids) {
        List<Book> books = bookRepo.findAllById(ids);
        return bookViewMapper.toBookView(books);
    }

    public List<BookView> findAll() {
        return bookRepo.findAll().stream().map(e -> bookViewMapper.toBookView(e)).collect(Collectors.toList());
    }
}
