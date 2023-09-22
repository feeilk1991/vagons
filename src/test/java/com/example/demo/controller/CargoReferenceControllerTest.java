package com.example.demo.controller;

import com.example.demo.service.CargoManifestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CargoReferenceController.class)
class CargoReferenceControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CargoManifestService cargoManifestService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void cargoReferenceList() {
    }

    @Test
    void cargoReferenceResponseEntity() {
    }

    @Test
    void createCargoReference() {
    }

    @Test
    void updateCargoReference() {
    }

    @Test
    void deleteCargoReference() {
    }
}