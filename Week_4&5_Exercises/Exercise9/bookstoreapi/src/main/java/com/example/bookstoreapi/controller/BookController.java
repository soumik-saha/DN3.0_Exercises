package main.java.com.example.bookstoreapi.controller;

import main.java.com.example.bookstoreapi.entity.Book;
import main.java.com.example.bookstoreapi.entity.Customer;
import main.java.com.example.bookstoreapi.exception.BookNotFoundException;
import main.java.com.example.bookstoreapi.exception.CustomerNotFoundException;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/books")
public class BookController {

    private List<Book> books = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<EntityModel<Book>>> getAllBooks() {
        List<EntityModel<Book>> bookResources = books.stream()
                .map(book -> EntityModel.of(book,
                        linkTo(methodOn(BookController.class).getBookById(book.getId())).withSelfRel(),
                        linkTo(methodOn(BookController.class).getAllBooks()).withRel("books")))
                .collect(Collectors.toList());
        return new ResponseEntity<>(bookResources, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EntityModel<Book>> createBook(@Valid @RequestBody Book book) {
        books.add(book);
        EntityModel<Book> bookResource = EntityModel.of(book,
                linkTo(methodOn(BookController.class).getBookById(book.getId())).withSelfRel(),
                linkTo(methodOn(BookController.class).getAllBooks()).withRel("books"));
        return new ResponseEntity<>(bookResource, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Book>> updateBook(@PathVariable int id, @Valid @RequestBody Book book) {
        if (id >= books.size()) {
            throw new BookNotFoundException("Book not found with id: " + id);
        }
        books.set(id, book);
        EntityModel<Book> bookResource = EntityModel.of(book,
                linkTo(methodOn(BookController.class).getBookById(book.getId())).withSelfRel(),
                linkTo(methodOn(BookController.class).getAllBooks()).withRel("books"));
        return new ResponseEntity<>(bookResource, HttpStatus.OK);
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

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Book>> getBookById(@PathVariable int id) {
        if (id >= books.size()) {
            throw new BookNotFoundException("Book not found with id: " + id);
        }
        Book book = books.get(id);
        EntityModel<Book> bookResource = EntityModel.of(book,
                linkTo(methodOn(BookController.class).getBookById(book.getId())).withSelfRel(),
                linkTo(methodOn(BookController.class).getAllBooks()).withRel("books"));
        return new ResponseEntity<>(bookResource, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<EntityModel<Book>>> searchBooks(@RequestParam(required = false) String title,
            @RequestParam(required = false) String author) {
        List<EntityModel<Book>> bookResources = books.stream()
                .filter(book -> (title == null || book.getTitle().contains(title)) &&
                        (author == null || book.getAuthor().contains(author)))
                .map(book -> EntityModel.of(book,
                        linkTo(methodOn(BookController.class).getBookById(book.getId())).withSelfRel(),
                        linkTo(methodOn(BookController.class).getAllBooks()).withRel("books")))
                .collect(Collectors.toList());
        return new ResponseEntity<>(bookResources, HttpStatus.OK);
    }

    @PostMapping("/customers")
    public ResponseEntity<EntityModel<Customer>> createCustomer(@Valid @RequestBody Customer customer) {
        customers.add(customer);
        EntityModel<Customer> customerResource = EntityModel.of(customer,
                linkTo(methodOn(BookController.class).getCustomerById(customer.getId())).withSelfRel(),
                linkTo(methodOn(BookController.class).getAllCustomers()).withRel("customers"));
        return new ResponseEntity<>(customerResource, HttpStatus.CREATED);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<EntityModel<Customer>> getCustomerById(@PathVariable int id) {
        if (id >= customers.size()) {
            throw new CustomerNotFoundException("Customer not found with id: " + id);
        }
        Customer customer = customers.get(id);
        EntityModel<Customer> customerResource = EntityModel.of(customer,
                linkTo(methodOn(BookController.class).getCustomerById(customer.getId())).withSelfRel(),
                linkTo(methodOn(BookController.class).getAllCustomers()).withRel("customers"));
        return new ResponseEntity<>(customerResource, HttpStatus.OK);
    }

    @GetMapping("/customers")
    public ResponseEntity<List<EntityModel<Customer>>> getAllCustomers() {
        List<EntityModel<Customer>> customerResources = customers.stream()
                .map(customer -> EntityModel.of(customer,
                        linkTo(methodOn(BookController.class).getCustomerById(customer.getId())).withSelfRel(),
                        linkTo(methodOn(BookController.class).getAllCustomers()).withRel("customers")))
                .collect(Collectors.toList());
        return new ResponseEntity<>(customerResources, HttpStatus.OK);
    }
}