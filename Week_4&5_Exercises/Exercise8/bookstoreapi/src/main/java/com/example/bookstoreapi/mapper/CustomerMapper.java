package main.java.com.example.bookstoreapi.mapper;

import main.java.com.example.bookstoreapi.dto.CustomerDTO;
import main.java.com.example.bookstoreapi.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO customerToCustomerDTO(Customer customer);

    Customer customerDTOToCustomer(CustomerDTO customerDTO);
}