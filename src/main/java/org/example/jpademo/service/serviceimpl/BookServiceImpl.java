package org.example.jpademo.service.serviceimpl;

import org.example.jpademo.model.Author;
import org.example.jpademo.model.Book;
import org.example.jpademo.model.Category;
import org.example.jpademo.model.request.BookRequest;
import org.example.jpademo.model.response.BookResponse;
import org.example.jpademo.model.response.CategoryResponse;
import org.example.jpademo.repository.AuthorRepository;
import org.example.jpademo.repository.BookRepository;
import org.example.jpademo.repository.CategoryRepository;
import org.example.jpademo.service.BookService;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, CategoryRepository categoryRepository, ModelMapper mapper) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    @Override
    public void createNewBook(BookRequest bookRequest) {
        Author author = authorRepository.findById(bookRequest.getAuthorId()).orElseThrow();
        List<Category> categories = bookRequest.getCategoriesId().stream()
                .map(categoryId -> categoryRepository.findById(categoryId)
                        .orElseThrow(() -> new RuntimeException("Category not found")))
                .collect(Collectors.toList());
        Book book = new Book();
        book.setTitle(bookRequest.getTitle());
        book.setReleaseYear(bookRequest.getReleaseYear());
        book.setAuthor(author);
        book.setCategories(categories);
        bookRepository.save(book);
    }

    @Override
    @Transactional
    public BookResponse getBookId(UUID bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow();
        BookResponse bookResponse = new BookResponse();
        bookResponse.setBookId(book.getBookId());
        bookResponse.setReleaseYear(book.getReleaseYear());
        bookResponse.setTitle(book.getTitle());
        bookResponse.setAuthor(book.getAuthor());
        List<CategoryResponse> categoryResponses = book.getCategories().stream()
                .map(category -> new CategoryResponse(category.getCategoryID(),category.getCategoryName()))
                .collect(Collectors.toList());
        bookResponse.setCategories(categoryResponses);
        return bookResponse;
    }

    @Override
    @Transactional
    public List<BookResponse> getBookByTitle(String title) {
        List<Book> books = bookRepository.findBooksByTitle(title);
        return books.stream().map(book -> {
            BookResponse bookResponse = new BookResponse();
            bookResponse.setBookId(book.getBookId());
            bookResponse.setReleaseYear(book.getReleaseYear());
            bookResponse.setTitle(book.getTitle());
            bookResponse.setAuthor(book.getAuthor());
            List<CategoryResponse> categoryResponses = book.getCategories().stream()
                    .map(category -> new CategoryResponse(category.getCategoryID(), category.getCategoryName()))
                    .collect(Collectors.toList());
            bookResponse.setCategories(categoryResponses);
            return bookResponse;
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public BookResponse updateBookById(UUID bookId, BookRequest bookRequest) {
        // Fetch the book and author as before
        Book book = bookRepository.findById(bookId).orElseThrow();
        Author author = authorRepository.findById(bookRequest.getAuthorId()).orElseThrow();

        // Explicitly initialize the lazy collection
        book.getCategories().forEach(category -> {
            Hibernate.initialize(category.getBooks()); // Initialize the collection
        });

        // Delete associations in the join table
        bookRepository.deleteBookInBookCategoryByBookId(bookId);  // This should execute correctly now

        // Handle the rest of the update logic
        List<Category> categories = bookRequest.getCategoriesId().stream()
                .map(categoryId -> categoryRepository.findById(categoryId)
                        .orElseThrow(() -> new RuntimeException("Category not found")))
                .collect(Collectors.toList());
        book.setTitle(bookRequest.getTitle());
        book.setAuthor(author);
        book.setCategories(categories);
        book.setReleaseYear(bookRequest.getReleaseYear());

        bookRepository.save(book);

        // Prepare the response
        BookResponse bookResponse = new BookResponse();
        bookResponse.setBookId(book.getBookId());
        bookResponse.setReleaseYear(book.getReleaseYear());
        bookResponse.setTitle(book.getTitle());
        bookResponse.setAuthor(book.getAuthor());
        bookResponse.setCategories(categories.stream()
                .map(category -> new CategoryResponse(category.getCategoryID(), category.getCategoryName()))
                .collect(Collectors.toList()));

        return bookResponse;
    }

    @Override
    public void deleteBookById(UUID bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow();
        bookRepository.delete(book);
    }

    @Override
    public List<BookResponse> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        List<BookResponse> bookResponses = new ArrayList<>();
        for(Book book : books){
            BookResponse bookResponse = mapper.map(book, BookResponse.class);
            bookResponses.add(bookResponse);
        }
        return bookResponses;
    }


}
