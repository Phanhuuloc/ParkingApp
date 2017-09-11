//package com.sungwoo.aps.test;
//
//import com.sungwoo.aps.ApsApplication;
//import com.sungwoo.aps.commons.AreaStatus;
//import com.sungwoo.aps.commons.CarStatus;
//import com.sungwoo.aps.models.Area;
//import com.sungwoo.aps.models.Car;
//import com.sungwoo.aps.models.Lot;
//import com.sungwoo.aps.models.Parking;
//import com.sungwoo.aps.repo.AreaRepo;
//import com.sungwoo.aps.repo.CarRepo;
//import com.sungwoo.aps.repo.LotRepo;
//import com.sungwoo.aps.repo.ParkingRepo;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(
//        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
//        classes = ApsApplication.class)
//@AutoConfigureMockMvc
//@AutoConfigureTestDatabase
//public class AreaRestControllerTests {
//    @Autowired
//    private MockMvc mvc;
//
//    @Autowired
//    private AreaRepo areaRepo;
//
//    @Autowired
//    private CarRepo carRepo;
//
//    @Autowired
//    private ParkingRepo parkingRepo;
//
//    @Autowired
//    private LotRepo lotRepo;
//
//    private Car bmw;
//    private Parking parkingA;
//    private Parking parkingB;
//    private Car iz;
//
//    @Before
//    public void init() {
//        // given
//        bmw = new Car("52B2-99999");
//        bmw.setStatus(CarStatus.ARRIVED.Value());
//        carRepo.saveAndFlush(bmw);
//
//        iz = new Car("52B2-99998");
//        iz.setStatus(CarStatus.PARKING.Value());
//        carRepo.saveAndFlush(iz);
//
//        parkingA = createParking("Etown2","A",false);
//        parkingB = createParking("Etown3","D",true);
//    }
//
//    private Parking createParking(String pName, String lName, boolean isFull) {
//        Parking parking = new Parking(pName);
//        Lot lot = new Lot(lName);
//
//        Set<Lot> lots = new HashSet<>();
//        lots.add(lot);
//
//        Set<Area> areas = new HashSet<>();
//        for (int i = 0; i < 2; i++) {
//            Area area = new Area(lName + i, isFull ? AreaStatus.FULL.Value() : AreaStatus.EMPTY.Value());
//            area.setLot(lot);
//            areas.add(area);
//        }
//
//        lot.setAreas(areas);
//        lot.setParking(parking);
//        parking.setLots(lots);
//
//        parkingRepo.saveAndFlush(parking);
//        return parking;
//    }
//
//    @After
//    public void resetDb() {
//        carRepo.deleteAll();
//        areaRepo.deleteAll();
//    }
//
//    //case 1 parking has empty area
//    @Test
//    public void givenACar_andEmptyParking_whenRequestParking_thenStatus200()
//            throws Exception {
//        mvc.perform(post("/area/parking")
//                .contentType(MediaType.APPLICATION_JSON)
//                .param("car", String.valueOf(bmw.getUid()))
//                .param("p", String.valueOf(parkingA.getUid())))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
//    }
//
//    //case 1 parking has full area
//    @Test
//    public void givenACar_andFullParking_whenRequestParking_thenStatus200()
//            throws Exception {
//        mvc.perform(post("/area/parking")
//                .contentType(MediaType.APPLICATION_JSON)
//                .param("car", String.valueOf(bmw.getUid()))
//                .param("p", String.valueOf(parkingB.getUid())))
//                .andExpect(status().isLocked());
//    }
//
//}
