package org.example.jpademo.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.example.jpademo.model.request.BookRequest;
import org.example.jpademo.model.response.APIResponse;
import org.example.jpademo.model.response.BookResponse;
import org.example.jpademo.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("create-book")
    @Operation(summary = "Create new Book")
    public ResponseEntity<?> createNewBook(@RequestBody BookRequest bookRequest){
        bookService.createNewBook(bookRequest);
        return new ResponseEntity<>(
                new APIResponse<>("New Book Created Successfully",
                        HttpStatus.CREATED,null,201, LocalDateTime.now()
                ), HttpStatus.CREATED);
    }

    @GetMapping("get-book-id/{bookId}")
    @Operation(summary = "Get Book by Book ID")
    public ResponseEntity<?> getBookById(@PathVariable UUID bookId){
        BookResponse book = bookService.getBookId(bookId);
        return new ResponseEntity<>(
                new APIResponse<>("Get Book By ID Successfully",
                        HttpStatus.OK,book,200, LocalDateTime.now()
                ), HttpStatus.OK);
    }

    @GetMapping("get-book-title/{title}")
    @Operation(summary = "Get Book by Title")
    public ResponseEntity<?> getBookByTitle(@PathVariable String title){
        List<BookResponse> books = bookService.getBookByTitle(title);
        return new ResponseEntity<>(
                new APIResponse<>("Get Books By Title Successfully",
                        HttpStatus.OK,books,200, LocalDateTime.now()
                ), HttpStatus.OK);
    }
    @PutMapping("update-book-by-id/{bookId}")
    @Operation(summary = "Update book by ID")
    public ResponseEntity<?> updateBookById(@PathVariable UUID bookId,@RequestBody BookRequest bookRequest){
        BookResponse books = bookService.updateBookById(bookId,bookRequest);
        return new ResponseEntity<>(
                new APIResponse<>("Updated Books By ID Successfully",
                        HttpStatus.OK,books,200, LocalDateTime.now()
                ), HttpStatus.OK);
    }
    @DeleteMapping("delete-book-id/{bookId}")
    @Operation(summary = "Delete book by ID")
    public ResponseEntity<?> deleteBookById(@PathVariable UUID bookId){
        bookService.deleteBookById(bookId);
        return new ResponseEntity<>(
                new APIResponse<>("Deleted Books By ID Successfully",
                        HttpStatus.OK,null,200, LocalDateTime.now()
                ), HttpStatus.OK);
    }
    @GetMapping("get-all-book")
    @Operation(summary = "Get all books")
    public ResponseEntity<?> getAllBooks(){
        List<BookResponse> bookResponses = bookService.getAllBooks();
        return new ResponseEntity<>(
                new APIResponse<>("Get all Books Successfully",
                        HttpStatus.OK,bookResponses,200, LocalDateTime.now()
                ), HttpStatus.OK);
    }
}
