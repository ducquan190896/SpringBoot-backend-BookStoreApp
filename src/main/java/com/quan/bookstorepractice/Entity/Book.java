package com.quan.bookstorepractice.Entity;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Table(name = "book")
@Entity(name = "Book")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Book {
    @Id
    @SequenceGenerator(
        name = "book_sequence",
        sequenceName = "book_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "book_sequence"
    )
    @Column(name = "id", updatable = false)
    private Long id;

    @NonNull
    @NotBlank(message = "title of the book cannot be blank")
    @Column(name = "title", nullable = false)
    private String title;

    @NonNull
    @NotBlank(message = "author cannot be blank")
    @Column(name = "author", nullable = false)
    private String author;

     @NonNull
   
    @Column(name = "year", nullable = false)
    private int year;

    @NonNull
    @Min(value = 0, message = "price cannot be lower than 0")
    // @NotBlank(message = "price of the book cannot be blank")
    @Column(name = "price", nullable = false)
    private double price;

    @NonNull
    // @JsonManagedReference
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(
        name = "category_id",
        referencedColumnName = "id"
    )
    private Category category;

    public Book( String title,
            String author, int year,
            double price) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.price = price;
    }
    
}
