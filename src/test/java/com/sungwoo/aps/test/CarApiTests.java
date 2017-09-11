//package com.sungwoo.aps.test;
//
//import com.sungwoo.aps.controllers.CarController;
//import com.sungwoo.aps.controllers.LotController;
//import com.sungwoo.aps.models.Car;
//import com.sungwoo.aps.models.Lot;
//import com.sungwoo.aps.repo.CarRepo;
//import com.sungwoo.aps.repo.LotRepo;
//import com.sungwoo.aps.services.CarService;
//import com.sungwoo.aps.services.LotService;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(CarController.class)
//public class CarApiTests {
//    @Autowired
//    private MockMvc mvc;
//
//    @MockBean
//    private CarService carService;
//
//    @Test
//    public void testFindLotByUid() throws Exception {
//        given(this.carService.findByUid(1))
//                .willReturn(new Car(1, "50B2-99999"));
//        mvc.perform(get("/car/info/1"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
//    }
//}