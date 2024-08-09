package org.example.jpademo.repository;

import org.example.jpademo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
//    @Query("SELECT b FROM Book b WHERE b.title = :title") //Optional dak kor ban ot kor ban
    @Query(value = """
    SELECT * FROM book_tb WHERE title ILIKE '%' || :title || '%'
    """,nativeQuery = true) //Native Query
    List<Book> findBooksByTitle(@Param("title") String title);

    @Modifying
    @Transactional
    @Query(value = """
    DELETE FROM category_book_tb WHERE book_id = :bookId
    """, nativeQuery = true)
    void deleteBookInBookCategoryByBookId(@Param("bookId") UUID bookId);
}
