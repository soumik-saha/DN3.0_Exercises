package main.java.com.example.bookstoreapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BookDTO {
    @JsonProperty("book_id")
    private String id;

    @JsonProperty("book_title")
    private String title;

    @JsonProperty("book_author")
    private String author;

    @JsonProperty("book_price")
    private double price;
}