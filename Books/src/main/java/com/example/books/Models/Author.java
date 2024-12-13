package com.example.books.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@Entity
@RequiredArgsConstructor
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Необходимо ввести имя!")
    private String first_name;

    @NotEmpty(message = "Необходимо ввести фамилию!")
    private String last_name;

    public String getShortname(){
        return last_name + " " + first_name.charAt(0);
    }

    public String getFullname(){
        return first_name + " " + last_name;
    }

}
