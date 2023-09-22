package com.example.demo.controller;

import com.example.demo.service.PassportWagonService;
import com.example.demo.service.StationModelService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
@WebMvcTest(StationModelController.class)
class StationModelControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper; // ObjectMapper для преобразования объектов в JSON

    @MockBean
    private StationModelService stationModelService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findStationModel() {
    }

    @Test
    void findStationModelById() {
    }

    @Test
    void createStationModel() {
    }

    @Test
    void updateStationModel() {
    }

    @Test
    void deleteStation() {
    }
}
