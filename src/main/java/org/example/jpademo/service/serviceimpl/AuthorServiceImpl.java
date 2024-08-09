package org.example.jpademo.service.serviceimpl;

import org.example.jpademo.model.Author;
import org.example.jpademo.model.request.AuthorRequest;
import org.example.jpademo.repository.AuthorRepository;
import org.example.jpademo.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author createNewAuthor(AuthorRequest authorRequest) {
        Author author = new Author();
        author.setAuthorName(authorRequest.getAuthorName());
        author.setAuthorAge(authorRequest.getAuthorAge());
        author.setActive(authorRequest.getActive());
        authorRepository.save(author);
        return author;
    }

    @Override
    public List<Author> getAllAuthor() {
        return authorRepository.findAll();
    }

    @Override
    public Author updateAuthor(UUID authorId, AuthorRequest authorRequest) {
        Author author = authorRepository.findById(authorId).orElseThrow();
            author.setAuthorName(authorRequest.getAuthorName());
            author.setAuthorAge(authorRequest.getAuthorAge());
            author.setActive(authorRequest.getActive());
            authorRepository.save(author);
        return author;
    }

    @Override
    public void deleteAuthorById(UUID authorId) {
        Author author = authorRepository.findById(authorId).orElseThrow();
        authorRepository.delete(author);
    }

    @Override
    public Author getAuthorById(UUID authorId) {
        return authorRepository.findById(authorId).orElseThrow();
    }

    @Override
    public List<Author> getAuthorByName(String authorName) {
        return authorRepository.findAuthorByAuthorName(authorName);
    }
}
