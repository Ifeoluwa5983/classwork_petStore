package com.petstore.petstore.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    private Integer age;

    private String color;

    @Column(unique = false)
    private String breed;

    @Enumerated(EnumType.STRING)
    private Gender petSex;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @ToString.Exclude
    private Store store;
}
