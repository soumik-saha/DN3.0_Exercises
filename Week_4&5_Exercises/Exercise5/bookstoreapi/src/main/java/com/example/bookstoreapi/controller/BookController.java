package main.java.com.example.bookstoreapi.controller;

import main.java.com.example.bookstoreapi.entity.Book;
import main.java.com.example.bookstoreapi.entity.Customer;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    private List<Book> books = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "GetAllBooksHeader");
        return new ResponseEntity<>(books, headers, HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        books.add(book);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "CreateBookHeader");
        return new ResponseEntity<>(book, headers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Book book) {
        books.set(id, book);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "UpdateBookHeader");
        return new ResponseEntity<>(book, headers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteBook(@PathVariable int id) {
        books.remove(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "DeleteBookHeader");
        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "GetBookByIdHeader");
        return new ResponseEntity<>(books.get(id), headers, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(@RequestParam(required = false) String title,
            @RequestParam(required = false) String author) {
        List<Book> result = books.stream()
                .filter(book -> (title == null || book.getTitle().contains(title)) &&
                        (author == null || book.getAuthor().contains(author)))
                .collect(Collectors.toList());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "SearchBooksHeader");
        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "CreateCustomerHeader");
        return new ResponseEntity<>(customer, headers, HttpStatus.CREATED);
    }

    @PostMapping("/customers/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Customer> registerCustomer(@RequestParam String name, @RequestParam String email) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "RegisterCustomerHeader");
        return new ResponseEntity<>(customer, headers, HttpStatus.CREATED);
    }
}