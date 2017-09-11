//package com.sungwoo.aps.test;
//
//import com.sungwoo.aps.models.Car;
//import com.sungwoo.aps.models.Lot;
//import com.sungwoo.aps.repo.CarRepo;
//import com.sungwoo.aps.services.CarService;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//public class CarRepoTests {
//    @Autowired
//    private TestEntityManager entityManager;
//
//    @Autowired
//    private CarRepo carRepo;
//
//    @MockBean
//    private CarService carService;
//    private Car bmw;
//    private Car tesla;
//
//    @Before
//    public void init() {
//        // given
//        bmw = new Car("52B2-99999");
//        entityManager.persist(bmw);
//        entityManager.flush();
//
//        tesla = new Car("52B2-88888");
//        entityManager.persist(tesla);
//        entityManager.flush();
//
//        List<Lot> lots = Arrays.asList(
//                new Lot(1, "Lot 1"),
//                new Lot(2, "Lot 2"));
//    }
//
//    @Test
//    public void whenFindByLicense() {
//        // when
//        Car found = carRepo.findByLicense(bmw.getLicense());
//
//        // then
//        assertThat(found.getLicense())
//                .isEqualTo(bmw.getLicense());
//    }
//
//    @Test
//    public void whenFindByUid() {
//        // when
//        Car found = carRepo.findByUid(bmw.getUid());
//
//        // then
//        assertThat(found.getUid())
//                .isEqualTo(bmw.getUid());
//    }
//}
