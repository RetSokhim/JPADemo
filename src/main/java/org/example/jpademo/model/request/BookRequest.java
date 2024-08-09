package org.example.jpademo.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {
    private String title;
    @DateTimeFormat(pattern = "YYYY-MM-DD")
    private LocalDate releaseYear;
    private UUID authorId;
    private List<UUID> categoriesId;
}
