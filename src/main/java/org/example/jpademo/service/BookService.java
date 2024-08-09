package org.example.jpademo.service;

import org.example.jpademo.model.Book;
import org.example.jpademo.model.request.BookRequest;
import org.example.jpademo.model.response.BookResponse;

import java.util.List;
import java.util.UUID;

public interface BookService {
    void createNewBook(BookRequest bookRequest);
    BookResponse getBookId(UUID bookId);
    List<BookResponse> getBookByTitle(String title);
    BookResponse updateBookById(UUID bookId, BookRequest bookRequest);
    void deleteBookById(UUID bookId);
    List<BookResponse> getAllBooks();
}
