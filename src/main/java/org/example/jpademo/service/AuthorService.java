package org.example.jpademo.service;

import org.example.jpademo.model.Author;
import org.example.jpademo.model.request.AuthorRequest;

import java.util.List;
import java.util.UUID;

public interface AuthorService {
    Author createNewAuthor(AuthorRequest authorRequest);
    List<Author> getAllAuthor();
    Author updateAuthor(UUID authorId, AuthorRequest authorRequest);
    void deleteAuthorById(UUID authorId);
    Author getAuthorById(UUID authorId);
    List<Author> getAuthorByName(String authorName);
}
