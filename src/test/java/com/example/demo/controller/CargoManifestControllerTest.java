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

@RunWith(SpringRunner.class)
@WebMvcTest(CargoManifestController.class)
class CargoManifestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CargoManifestService cargoManifestService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findCargoManifests() throws Exception {
    }

    @Test
    void cargoManifestResponseEntity() throws Exception {

    }

    @Test
    void createCargoManifest() throws Exception {

    }

    @Test
    void updateCargoManifest() throws Exception {
    }

    @Test
    void deleteCargoManifest() throws Exception {

    }
}