package com.sungwoo.aps.test;

import com.sungwoo.aps.ApsApplication;
import com.sungwoo.aps.commons.CarStatus;
import com.sungwoo.aps.models.Car;
import com.sungwoo.aps.repo.CarRepo;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = ApsApplication.class)
@AutoConfigureMockMvc
//@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@AutoConfigureTestDatabase
public class CarRestControllerTests {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private CarRepo carRepo;
    private Car bmw;

    @After
    public void resetDb() {
        carRepo.deleteAll();
    }

    // write test cases here
    @Test
    public void givenACar_whenGetCarInfo_thenStatus200()
            throws Exception {
        createTestCar();
        mvc.perform(get("/car/info/" + bmw.getUid())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.uid", is(bmw.getUid())))
                .andExpect(jsonPath("$.status", is(bmw.getStatus())));
    }

    private void createTestCar() {
        bmw = new Car();
        bmw.setStatus(CarStatus.PARKING.value());
        carRepo.saveAndFlush(bmw);
    }

}
