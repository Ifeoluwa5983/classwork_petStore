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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class PetRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper mapper;

    @BeforeEach
    void setUp() {
       mapper  = new ObjectMapper();
    }

    @Test
    void whenICallTheCreatePetPostMethod_thenCreateAPetObject() throws Exception {
        Pet pet = new Pet();
        pet.setName("silk");
        pet.setPetSex(Gender.FEMALE);
        pet.setColor("white");
        pet.setBreed("Dog");
        pet.setAge(5);

        this.mockMvc.perform(post("/pet/create")
                .contentType("application/json")
                .content(mapper.writeValueAsString(pet)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

    }
    @Test
    void whenICallTheGetPetMethod_thenCreateAPetObject() throws Exception {

        this.mockMvc.perform(get("/pet/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void whenICallTheFindMethod_thenReturnPetObject() throws Exception {

        this.mockMvc.perform(get("/pet/one/7"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void whenICallTheDeleteMethod_thenDeleteThePetObject() throws Exception {

        this.mockMvc.perform(delete("/pet/one/7"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }


}