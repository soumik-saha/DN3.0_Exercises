package main.java.com.example.bookstoreapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CustomerDTO {
    @JsonProperty("customer_id")
    private String id;

    @JsonProperty("customer_name")
    private String name;

    @JsonProperty("customer_email")
    private String email;

    @JsonProperty("customer_phone_number")
    private String phoneNumber;

    @JsonProperty("customer_address")
    private String address;
}