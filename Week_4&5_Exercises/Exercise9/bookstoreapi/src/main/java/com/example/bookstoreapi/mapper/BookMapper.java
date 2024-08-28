package main.java.com.example.bookstoreapi.mapper;

import main.java.com.example.bookstoreapi.dto.BookDTO;
import main.java.com.example.bookstoreapi.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookDTO bookToBookDTO(Book book);

    Book bookDTOToBook(BookDTO bookDTO);
}