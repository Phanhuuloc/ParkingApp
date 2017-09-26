//package com.sungwoo.aps.test;
//
//import com.sungwoo.aps.controllers.CarController;
//import com.sungwoo.aps.domain.Car;
//import com.sungwoo.aps.services.CarService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
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
//                .willReturn(new Car());
//        mvc.perform(get("/car/info/1"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
//    }
//}