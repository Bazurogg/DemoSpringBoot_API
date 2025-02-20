package fr.afpa.pompey.cda.demospringboot_api.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="Person")

public class Person {
    //primary key
    @Id
    //autoincrement
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Integer id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;
}
