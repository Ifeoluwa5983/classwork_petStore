package com.petstore.petstore.data.model;
import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @Column(nullable = false)
    private String name;

    private String location;

    private String contactNumber;

//    @JoinColumn
//    @OneToMany
//    private List<Pet> pets;
}
