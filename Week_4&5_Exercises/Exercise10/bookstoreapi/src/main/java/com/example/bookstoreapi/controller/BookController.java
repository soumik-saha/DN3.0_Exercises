package main.java.com.example.bookstoreapi.controller;

import main.java.com.example.bookstoreapi.entity.Book;
import main.java.com.example.bookstoreapi.entity.Customer;
import main.java.com.example.bookstoreapi.exception.BookNotFoundException;
import main.java.com.example.bookstoreapi.exception.CustomerNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    private List<Book> books = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<List<Book>> getAllBooks() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "GetAllBooksHeader");
        return new ResponseEntity<>(books, headers, HttpStatus.OK);
    }

    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        books.add(book);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "CreateBookHeader");
        return new ResponseEntity<>(book, headers, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        if (id >= books.size()) {
            throw new BookNotFoundException("Book not found with id: " + id);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "GetBookByIdHeader");
        return new ResponseEntity<>(books.get(id), headers, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<Book> updateBook(@PathVariable int id, @Valid @RequestBody Book book) {
        if (id >= books.size()) {
            throw new BookNotFoundException("Book not found with id: " + id);
        }
        books.set(id, book);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "UpdateBookHeader");
        return new ResponseEntity<>(book, headers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteBook(@PathVariable int id) {
        if (id >= books.size()) {
            throw new BookNotFoundException("Book not found with id: " + id);
        }
        books.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

    