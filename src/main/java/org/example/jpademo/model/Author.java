package org.example.jpademo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "author_tb")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "author_id")
    private UUID authorId;
    @Column(name = "author_name")
    private String authorName;
    @Column(name = "author_age")
    private Integer authorAge;
    @Column(name = "author_active")
    private String active;
}
