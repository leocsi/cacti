package com.example.cacti.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.fail;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles({"h2", "dbseeder"})
class ShipperControllerTest {
    private static final String SHIPPER_ENDPOINT_URL = "/api/shippers";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void testGETAll() throws Exception {
        fail();
    }

    @Test
    public void testGETById_Success() throws Exception {
        fail();
    }

    @Test
    public void testGETById_Failure() throws Exception {
        fail();
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

//    @Test
//    // After the test, restore the database to the initial state
//    @DirtiesContext
//    public void testPOST_success() throws Exception {
//        fail();
//    }
//
//    @Test
//    @DirtiesContext
//    void testPOST_failure() throws Exception {
//        fail();
//    }
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

}