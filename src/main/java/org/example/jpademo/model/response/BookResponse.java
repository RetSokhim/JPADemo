package org.example.jpademo.model.response;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.jpademo.model.Author;
import org.example.jpademo.model.Category;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {
    private UUID bookId;
    private String title;
    private LocalDate releaseYear;
    private Author author;
    private List<CategoryResponse> categories;
}
