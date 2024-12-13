package com.example.books.Models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Entity
@RequiredArgsConstructor
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Необходимо ввести название!")
    private String title;

    @NotEmpty(message = "Необходимо ввести код (ISBN)!")
    private String code;

    @Min(message = "Год должен быть больше 1800!", value = 1800)
    private int year_publish;

    @Min(message = "Кол-во страниц должено быть не меньше 1!", value = 1)
    private int count_page;

    @NotEmpty(message = "Необходимо указать тип переплёта!")
    private String hardcover;

    @NotEmpty(message = "Необходимо ввести реферат!")
    private String referat;

    @NotEmpty(message = "Необходимо ввести статус книги!")
    private String status;

    @ManyToOne
    @NotNull(message = "Необходимо выбрать автора!")
    private Author author;

    @ManyToOne
    @NotNull(message = "Необходимо выбрать издательства!")
    private Publish publish;

}
