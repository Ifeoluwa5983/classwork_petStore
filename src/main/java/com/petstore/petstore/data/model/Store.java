package com.petstore.petstore.data.model;
import lombok.Data;
import lombok.ToString;

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
    @ToString.Exclude
    private List<Pet> pets;


    public void addPets(Pet pet){
        if(pets == null){
            pets = new ArrayList<>();
        }
        pets.add(pet);
    }
    public List<Pet> findPets(Store store){
        if(store != null){
            List<Pet> pets = store.getPets();
        }
        return  pets;
    }

}
