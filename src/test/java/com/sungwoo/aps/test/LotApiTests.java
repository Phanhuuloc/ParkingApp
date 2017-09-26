//package com.sungwoo.aps.test;
//
//import com.sungwoo.aps.controllers.LotController;
//import com.sungwoo.aps.domain.Car;
//import com.sungwoo.aps.domain.Lot;
//import com.sungwoo.aps.repo.CarRepo;
//import com.sungwoo.aps.repo.LotRepo;
//import com.sungwoo.aps.services.LotService;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.MockitoAnnotations;
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
//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(LotController.class)
//public class LotApiTests {
//    @Autowired
//    private MockMvc mvc;
//
//    @MockBean
//    private LotService lotService;
//
//    @Test
//    public void testFindLotByUid() throws Exception {
//        given(this.lotService.findByUid(1))
//                .willReturn(new Lot(1, "Lot 1"));
//        mvc.perform(get("/lot/1"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
//    }
//
//}
