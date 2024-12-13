package com.example.books.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@Entity
@RequiredArgsConstructor
@Table(name = "publish")
public class Publish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Необходимо ввести название!")
    private String name_publish;

    @NotEmpty(message = "Необходимо ввести адрес!")
    private String address;

    @NotEmpty(message = "Необходимо ввести адрес сайта!")
    private String site;
}
