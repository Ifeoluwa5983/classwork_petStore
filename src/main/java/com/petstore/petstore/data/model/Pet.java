package com.petstore.petstore.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(unique = true)
    private String breed;

    private Gender petSex;

    @ManyToOne
//    @JoinColumn(name = "store")
    private Store store;
}
