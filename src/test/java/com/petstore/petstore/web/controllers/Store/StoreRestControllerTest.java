package com.petstore.petstore.web.controllers.Store;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:db/insert.sql"})
class StoreRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper mapper;


    @BeforeEach
    void setUp() {
    }

    @Test
    void addPetToStoreTest() throws Exception {

        this.mockMvc.perform(put("/store/addpet").param("storeId", "21").param("petId", "91"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
}