package com.petstore.petstore.web.controllers.Pet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petstore.petstore.data.model.Gender;
import com.petstore.petstore.data.model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class PetRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @Test
    void whenICallTheCreatePetPostMethod_thenCreateAPetObject() throws Exception {
        Pet pet = new Pet();
        pet.setName("silk");
        pet.setPetSex(Gender.FEMALE);
        pet.setColor("white");
        pet.setBreed("Dog");
        pet.setAge(5);

        ObjectMapper mapper = new ObjectMapper();


        this.mockMvc.perform(post("/pet/create")
                .contentType("application/json")
                .content(mapper.writeValueAsString(pet)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

    }
}