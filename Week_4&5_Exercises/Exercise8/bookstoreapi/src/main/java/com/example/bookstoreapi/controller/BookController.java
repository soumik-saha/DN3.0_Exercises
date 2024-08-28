package main.java.com.example.bookstoreapi.controller;

import main.java.com.example.bookstoreapi.dto.BookDTO;
import main.java.com.example.bookstoreapi.dto.CustomerDTO;
import main.java.com.example.bookstoreapi.entity.Book;
import main.java.com.example.bookstoreapi.entity.Customer;
import main.java.com.example.bookstoreapi.exception.BookNotFoundException;
import main.java.com.example.bookstoreapi.exception.CustomerNotFoundException;
import main.java.com.example.bookstoreapi.mapper.BookMapper;
import main.java.com.example.bookstoreapi.mapper.CustomerMapper;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "GetAllBooksHeader");
        List<BookDTO> bookDTOs = books.stream()
                .map(BookMapper.INSTANCE::bookToBookDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(bookDTOs, headers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@Valid @RequestBody BookDTO bookDTO) {
        Book book = BookMapper.INSTANCE.bookDTOToBook(bookDTO);
        books.add(book);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "CreateBookHeader");
        return new ResponseEntity<>(bookDTO, headers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable int id, @Valid @RequestBody BookDTO bookDTO) {
        if (id >= books.size()) {
            throw new BookNotFoundException("Book not found with id: " + id);
        }
        Book book = BookMapper.INSTANCE.bookDTOToBook(bookDTO);
        books.set(id, book);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "UpdateBookHeader");
        return new ResponseEntity<>(bookDTO, headers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteBook(@PathVariable int id) {
        if (id >= books.size()) {
            throw new BookNotFoundException("Book not found with id: " + id);
        }
        books.remove(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "DeleteBookHeader");
        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable int id) {
        if (id >= books.size()) {
            throw new BookNotFoundException("Book not found with id: " + id);
        }
        BookDTO bookDTO = BookMapper.INSTANCE.bookToBookDTO(books.get(id));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "GetBookByIdHeader");
        return new ResponseEntity<>(bookDTO, headers, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookDTO>> searchBooks(@RequestParam(required = false) String title,
            @RequestParam(required = false) String author) {
        List<BookDTO> bookDTOs = books.stream()
                .filter(book -> (title == null || book.getTitle().contains(title)) &&
                        (author == null || book.getAuthor().contains(author)))
                .map(BookMapper.INSTANCE::bookToBookDTO)
                .collect(Collectors.toList());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "SearchBooksHeader");
        return new ResponseEntity<>(bookDTOs, headers, HttpStatus.OK);
    }

    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        Customer customer = CustomerMapper.INSTANCE.customerDTOToCustomer(customerDTO);
        customers.add(customer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "CreateCustomerHeader");
        return new ResponseEntity<>(customerDTO, headers, HttpStatus.CREATED);
    }

    @PostMapping("/customers/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CustomerDTO> registerCustomer(@RequestParam String name, @RequestParam String email) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        CustomerDTO customerDTO = CustomerMapper.INSTANCE.customerToCustomerDTO(customer);
        customers.add(customer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "RegisterCustomerHeader");
        return new ResponseEntity<>(customerDTO, headers, HttpStatus.CREATED);
    }

}