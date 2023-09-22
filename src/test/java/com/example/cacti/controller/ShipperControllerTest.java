package com.example.cacti.controller;

import com.example.cacti.model.Shipper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.example.cacti.config.DBSeeder.DB_SEEDER_SHIPPERS;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles({"h2", "mysql"})
class ShipperControllerTest {
    private static final String SHIPPER_ENDPOINT_URL = "/api/shippers";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void testGETAll() throws Exception {
        String JSON = mockMvc.perform(
                        get(SHIPPER_ENDPOINT_URL)
                ).andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        List<Shipper> shippersFromDB = objectMapper.readValue(JSON, new TypeReference<>() {
        });

        // Then
        assertFalse(shippersFromDB.isEmpty());
        assertEquals(DB_SEEDER_SHIPPERS.size(), shippersFromDB.size());
    }

    @Test
    public void testGETById_Success() throws Exception {
        // Given an existing id
        int testId = 1;

        // When
        String JSON = this.mockMvc.perform(get(SHIPPER_ENDPOINT_URL + "/" + testId))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Shipper shipper = objectMapper.readValue(JSON, Shipper.class);

        // Then
        assertNotNull(shipper);
        assertEquals(testId, shipper.getId());
    }

    @Test
    public void testGETById_Failure() throws Exception {
        // Given a non-existing id
        int testId = 1000;

        // When
        this.mockMvc.perform(get(SHIPPER_ENDPOINT_URL + "/" + testId))
                .andDo(print())
                // Then
                .andExpect(status().isNotFound());
    }

//    @Test
//    void getAll() {
//    }
//
//    @Test
//    void getById() {
//    }
//
//    @Test
//    void addNewShipper() {
//    }
//
//    @Test
//    void deleteShipper() {
//    }
//
//    @Test
//    void testDeleteShipper() {
//    }
//
//    @Test
//    void readShipperPhoneList() {
//    }
//
//    @Test
//    void readShipperNames() {
//    }

    @Test
    // After the test, restore the database to the initial state
    @DirtiesContext
    public void testPOST_success() throws Exception {
        String testName = "John Doe";
        String testPhone = "082 65723900";

        Shipper testShipper = new Shipper(null, testName, testPhone);
        String JSONToSent = objectMapper.writeValueAsString(testShipper);

        List<Shipper> before = getAllShippers();

        // When
        String JSONReceived = this.mockMvc.perform(post(SHIPPER_ENDPOINT_URL + "/add")
                        .header("Content-Type", "application/json")
                        .content(JSONToSent))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();


        // Then
        List<Shipper> after = getAllShippers();
        assertEquals(before.size(), after.size() - 1);
    }

    @Test
    @DirtiesContext
    void testPOST_failure() throws Exception {
        fail();
    }
//
//    @Test
//    @DirtiesContext
//    public void testPUT_Success() throws Exception {
//        fail();
//    }
//
//    @Test
//    public void testPUT_Failure() throws Exception {
//        fail();
//    }
//
//    @Test
//    @DirtiesContext
//    public void testDELETE_Success() throws Exception {
//        fail();
//    }
//
//    @Test
//    public void testDELETE_Failure() throws Exception {
//        fail();
//    }


    private List<Shipper> getAllShippers() throws Exception {
        String JSON = mockMvc.perform(get(SHIPPER_ENDPOINT_URL))
                .andReturn()
                .getResponse()
                .getContentAsString();
        return objectMapper.readValue(JSON, new TypeReference<>() {
        });
    }
}