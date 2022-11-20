package com.quan.bookstorepractice.Entity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Table(name = "category")
@Entity(name = "Category")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Category {
    @Id
    @SequenceGenerator(
        name = "category_sequence",
        sequenceName = "category_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "category_sequence"
    )
    @Column(name = "id", updatable = false)
    private Long id;

    @NonNull
    @NotBlank(message = "name cannot be blank")
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @JsonIgnore
    // @JsonBackReference
    @OneToMany(mappedBy = "category",  cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Book> books = new ArrayList<>();
    

}
