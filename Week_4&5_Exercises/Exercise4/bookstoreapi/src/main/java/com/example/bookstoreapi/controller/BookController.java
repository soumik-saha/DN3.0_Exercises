package main.java.com.example.bookstoreapi.controller;

import main.java.com.example.bookstoreapi.entity.Book;
import main.java.com.example.bookstoreapi.entity.Customer;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    private List<Book> books = new ArrayList<>();

    @GetMapping
    public List<Book> getAllBooks() {
        return books;
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        books.add(book);
        return book;
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable int id, @RequestBody Book book) {
        books.set(id, book);
        return book;
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable int id) {
        books.remove(id);
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable int id) {
        return books.get(id);
    }

    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam(required = false) String title,
            @RequestParam(required = false) String author) {
        return books.stream()
                .filter(book -> (title == null || book.getTitle().contains(title)) &&
                        (author == null || book.getAuthor().contains(author)))
                .collect(Collectors.toList());
    }

    @PostMapping("/customers")
    public Customer createCustomer(@RequestBody Customer customer) {
        return customer;
    }

    @PostMapping("/customers/register")
    public Customer registerCustomer(@RequestParam String name, @RequestParam String email) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        return customer;
    }
}