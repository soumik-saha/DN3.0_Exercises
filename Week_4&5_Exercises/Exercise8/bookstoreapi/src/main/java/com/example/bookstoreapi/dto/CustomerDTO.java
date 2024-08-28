package main.java.com.example.bookstoreapi.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CustomerDTO {
    private String id;

    @NotNull
    @Size(min = 1, max = 100)
    private String name;

    @NotNull
    @Email
    private String email;

    @Size(min = 10, max = 15)
    private String phoneNumber;

    @Size(max = 255)
    private String address;
}