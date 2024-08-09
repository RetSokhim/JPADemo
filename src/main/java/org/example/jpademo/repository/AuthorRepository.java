package org.example.jpademo.repository;

import org.example.jpademo.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {

    @Query(value = """
    SELECT * FROM author_tb WHERE author_name ILIKE '%' || :authorName || '%'
    """,nativeQuery = true) //Native Query
    List<Author> findAuthorByAuthorName(String authorName);
}
