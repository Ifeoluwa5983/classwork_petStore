package com.petstore.petstore.data.model;
import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
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

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Pet> pets;

    public void addPets(Pet pet){
        if(pets == null){
            pets = new ArrayList<>();
        }
        pets.add(pet);
    }
}
