package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.*;


class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA: A 사용자가 10000원 주문
        statefulService1.order2("userA", 10000);

        //ThreadB: B 사용자가 20000원 주문
        statefulService2.order2("userB", 20000);

        //ThreadA: 사용자 A 주문 금액 조회R

        int price = statefulService1.getPrice();
        System.out.println("price = " + price);
//        System.out.println("userAPrice = " + userAPrice);
//        System.out.println("userBPrice = " + userBPrice);

//        assertThat(statefulService1.getPrice()).isEqualTo(20000);

    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}