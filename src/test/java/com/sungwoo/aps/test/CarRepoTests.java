//package com.sungwoo.aps.test;
//
//import com.sungwoo.aps.models.Car;
//import com.sungwoo.aps.repo.CarRepo;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.context.junit4.SpringRunner;
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
//    private Car bmw;
//    private Car tesla;
//
//    @Before
//    public void init() {
//        // given
//        bmw = new Car();
//        entityManager.persist(bmw);
//        entityManager.flush();
//
//        tesla = new Car();
//        entityManager.persist(tesla);
//        entityManager.flush();
//
////        List<Lot> lots = Arrays.asList(
////                new Lot(1, "Lot 1"),
////                new Lot(2, "Lot 2"));
//    }
//
//    @Test
//    public void whenFindByLicense() {
//        // when
//        Car found = carRepo.findByUid(bmw.getUid());
//
//        // then
//        assertThat(found.getUid())
//                .isEqualTo(bmw.getUid());
//    }
//
////    @Test
////    public void whenFindByUid() {
////        // when
////        Car found = carRepo.findByUid(bmw.getUid());
////
////        // then
////        assertThat(found.getUid())
////                .isEqualTo(bmw.getUid());
////    }
//}
