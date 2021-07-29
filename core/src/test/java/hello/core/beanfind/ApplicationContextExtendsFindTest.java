package hello.core.beanfind;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextExtendsFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부보 타입으로 조회 시, 자식이 둘 이상 있으면, 중복 오류가 발생한다")
    void findBeanByParentTypeDuplicate() {
        assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(DiscountPolicy.class));
    }

    // 성공 케이스
    @Test
    @DisplayName("부보 타입으로 조회 시, 자식이 둘 이상 있으면, 빈 이름을 지정하면 된다다")
    void findBeanByParentTypeBeanName() {
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("특정 하위 타입으로 조회")
    void findBeanBySubType() {
        RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    } // 구체화에 의존하기 때문에 별로 좋지않은 코드

    @Test
    @DisplayName("부모 타입으로 무도 조회하기")
    void findAllBeanByParentType() {
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        assertThat(beansOfType.size()).isEqualTo(2);
        for (String key : beansOfType.keySet()) { // iter 입력 후 Enter -> for문 자동생성
            System.out.println("key = " + key + "value = " + beansOfType.get(key)); // soutv 입력 후 Enter -> 출력문 자동생성
        }
    }

        @Test
        @DisplayName("부모 타입으로 무도 조회하기 - Object")
        void findAllBeanByObjectType() {
            Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
            for (String key : beansOfType.keySet()) {
                System.out.println("key = " + key + " value = " + beansOfType.get(key));
            }
        } // 스프링 빈에 등록된 모든 객체가 다 나옴
        
    @Configuration
    static class TestConfig {

        @Bean
        public DiscountPolicy rateDiscountPolicy() { // DiscountPolicy로 한 이유는 의존관계가 한눈에 보이기 때문
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }
    }
}

